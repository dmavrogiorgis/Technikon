package gr.scytalys.team3.Technikon.service.impl;

import gr.scytalys.team3.Technikon.dto.PropertyDTO;
import gr.scytalys.team3.Technikon.dto.RepairCreateDTO;
import gr.scytalys.team3.Technikon.dto.RepairResponseDTO;
import gr.scytalys.team3.Technikon.mapper.PropertyMapper;
import gr.scytalys.team3.Technikon.mapper.RepairMapper;
import gr.scytalys.team3.Technikon.model.Property;
import gr.scytalys.team3.Technikon.model.PropertyOwner;
import gr.scytalys.team3.Technikon.model.Repair;
import gr.scytalys.team3.Technikon.repository.PropertyOwnerRepository;
import gr.scytalys.team3.Technikon.repository.PropertyRepository;
import gr.scytalys.team3.Technikon.repository.RepairRepository;
import gr.scytalys.team3.Technikon.repository.RepairSpecifications;
import gr.scytalys.team3.Technikon.service.PropertyService;
import gr.scytalys.team3.Technikon.service.RepairService;
import gr.scytalys.team3.Technikon.service.RepairValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class RepairServiceImpl implements RepairService {
    private RepairRepository repairRepository;
    private RepairMapper repairMapper;
    private PropertyService propertyService;
    private PropertyMapper propertyMapper;
    private RepairValidator repairValidator;


    @Override
    @Transactional
    public RepairResponseDTO createRepair(RepairCreateDTO repairCreateDTO) {
         propertyService.getPropertyById(repairCreateDTO.getPropertyId());

        Repair repair =  repairMapper.toRepair(repairCreateDTO);
        switch(repair.getTypeOfRepair()){
            case FRAMES -> repair.setCostOfRepair(BigDecimal.valueOf(110));
            case PAINTING -> repair.setCostOfRepair(BigDecimal.valueOf(100));
            case PLUMBING -> repair.setCostOfRepair(BigDecimal.valueOf(150));
            case INSULATION -> repair.setCostOfRepair(BigDecimal.valueOf(300));
            case ELECTRICAL_WORK -> repair.setCostOfRepair(BigDecimal.valueOf(400));
        }
        //validation impl awaits
        return repairMapper.toRepairResponseDTO(repairRepository.save(repair));
    }



    @Override
    public RepairResponseDTO getRepairById(long repairId) {
       Repair repair =  repairRepository.findById(repairId)
               .filter(Repair::isActive)
               .orElseThrow();
        log.info("Retrieved Repair with id: " + repairId);
        return repairMapper.toRepairResponseDTO(repair);
    }

    @Override
    public List<RepairResponseDTO> getRepairsByPropertyId(long propertyId) {
        List<Repair> repairs = repairRepository.findAllByPropertyPropertyId(propertyId);
        //validate.
        return repairs.stream().filter(Repair::isActive)
                .map(repairMapper::toRepairResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RepairResponseDTO> findRepairsByRepairDate(LocalDate dateTime) {
        List<Repair> repairs = repairRepository.findRepairsByRepairDate(dateTime);
        return repairs.stream()
                .map(repairMapper::toRepairResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RepairResponseDTO> getRepairByRangeOfDates(LocalDate startDate, LocalDate endDate) {
        //validation
        if(startDate.isAfter(endDate)) {
            log.debug("StartDate cant be later than endDate");
        }
        if(startDate.equals(endDate)){
           return findRepairsByRepairDate(startDate);
        }
        if(startDate.isAfter(endDate)){
            log.debug("end date is before start date");
            throw new EntityNotFoundException("start date is after end date");
        }
        List<Repair> repairs = repairRepository
                .findAll(RepairSpecifications
                        .repairDateBetween(startDate, endDate));

        return repairs.stream()
                .filter(Repair::isActive)
                .map(repairMapper::toRepairResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RepairResponseDTO> getRepairsByPropertyOwnerId(long propertyOwnerId) {
        List<PropertyDTO> propertyDTOS = propertyService.findAllPropertiesByOwnerId(propertyOwnerId);
        List<RepairResponseDTO> repairs = propertyDTOS.stream()
                                                      .map(PropertyDTO::getId)
                                                      .flatMap(id -> getRepairsByPropertyId(id).stream())
                                                      .distinct()
                                                      .toList();
//        validate
        return repairs;
    }
//
    @Override
    public BigDecimal getTotalCost(long propertyId) {
        List<RepairResponseDTO> repairs = getRepairsByPropertyId(propertyId);
        BigDecimal cost = BigDecimal.valueOf(0);
        for(RepairResponseDTO repair : repairs){
            cost = cost.add(repair.getCostOfRepair());
        }
        return cost;
    }

    @Transactional
    public boolean deleteRepair(long repairId){
        boolean isDeleted = false;
        Repair repair = repairRepository.findById(repairId).orElseThrow();
        // validation
        if(!repair.getStatusOfRepair().toString().equals("PENDING")){
            log.debug("Repair deactivated due to status progress");
            repair.setActive(false);
            return isDeleted;
        }
        repairRepository.delete(repair);
        log.debug("Deleted repair with id:");
        isDeleted = true;
        return isDeleted;
    }

    @Transactional
    @Override
    public RepairResponseDTO updateRepair(long repairId, RepairResponseDTO repairResponseDTO) {
        Repair repairDB = repairRepository.findById(repairId).orElseThrow();
        //validation
        if(repairResponseDTO.getStatusOfRepair() != null){
            repairDB.setStatusOfRepair(repairResponseDTO.getStatusOfRepair());
        }
        if(repairResponseDTO.getTypeOfRepair() != null){
            repairDB.setTypeOfRepair(repairResponseDTO.getTypeOfRepair());
        }
        if(repairResponseDTO.getCostOfRepair() != null){
            repairDB.setCostOfRepair(repairResponseDTO.getCostOfRepair());
        }
        if(repairResponseDTO.getRepairDate() != null){
            repairDB.setRepairDate(repairResponseDTO.getRepairDate());
        }
        if(repairResponseDTO.getDescription() != null){
            repairDB.setDescription(repairResponseDTO.getDescription());
        }
        if(Long.valueOf(repairId) != null){
            Property property = propertyMapper.toProperty(propertyService.getPropertyById(repairResponseDTO.getPropertyId()));
            repairDB.setProperty(property);
        }
        repairRepository.save(repairDB);
        return repairMapper.toRepairResponseDTO(repairDB);
    }
}
