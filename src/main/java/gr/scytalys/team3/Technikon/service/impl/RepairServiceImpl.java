package gr.scytalys.team3.Technikon.service.impl;

import gr.scytalys.team3.Technikon.dto.PropertyDTO;
import gr.scytalys.team3.Technikon.dto.RepairCreateDTO;
import gr.scytalys.team3.Technikon.dto.RepairResponseDTO;
import gr.scytalys.team3.Technikon.dto.RepairUpdateDTO;
import gr.scytalys.team3.Technikon.mapper.PropertyMapper;
import gr.scytalys.team3.Technikon.mapper.RepairMapper;
import gr.scytalys.team3.Technikon.model.Repair;
import gr.scytalys.team3.Technikon.model.StatusOfRepair;
import gr.scytalys.team3.Technikon.repository.RepairRepository;
import gr.scytalys.team3.Technikon.repository.specifications.RepairSpecifications;
import gr.scytalys.team3.Technikon.service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class RepairServiceImpl implements RepairService {
    private RepairRepository repairRepository;
    private RepairMapper repairMapper;
    private PropertyService propertyService;
    private RepairValidator repairValidator;

    @Override
    @Transactional
    public RepairResponseDTO createRepair(long propertyId, RepairCreateDTO repairCreateDTO) {

        repairValidator.checkForNull(repairCreateDTO);
        repairValidator.validateRepairCreation(repairCreateDTO);
        if(propertyId != repairCreateDTO.getPropertyId()){
            throw new InvalidParameterException("Cannot create repair with different propertyId from the user's");
        }
        Repair repair = repairMapper.toRepair(repairCreateDTO);
        repair.setStatusOfRepair(StatusOfRepair.PENDING);
        switch (repair.getTypeOfRepair()) {
            case FRAMES -> repair.setCostOfRepair(BigDecimal.valueOf(110));
            case PAINTING -> repair.setCostOfRepair(BigDecimal.valueOf(100));
            case PLUMBING -> repair.setCostOfRepair(BigDecimal.valueOf(150));
            case INSULATION -> repair.setCostOfRepair(BigDecimal.valueOf(300));
            case ELECTRICAL_WORK -> repair.setCostOfRepair(BigDecimal.valueOf(400));
        }
        return repairMapper.toRepairResponseDTO(repairRepository.save(repair));
    }


    @Override
    public RepairResponseDTO getRepairById(long repairId) {
        Repair repair = repairRepository.findById(repairId)
                .filter(Repair::isActive)
                .orElseThrow();
        log.info("Retrieved Repair with id: " + repairId);
        RepairResponseDTO repairResponseDTO = repairMapper.toRepairResponseDTO(repair);
        repairValidator.checkForNull(repairResponseDTO);
        repairValidator.validateRepairResponse(repairResponseDTO);
        return repairResponseDTO;
    }

    @Override
    public List<RepairResponseDTO> getRepairsByPropertyId(long propertyId) {
        List<Repair> repairs = repairRepository.findAllByPropertyPropertyId(propertyId);
        List<RepairResponseDTO> repairResponseDTOS = repairs.stream().filter(Repair::isActive)
                .map(repairMapper::toRepairResponseDTO)
                .peek(repairResponseDTO -> {
                    repairValidator.checkForNull(repairResponseDTO);
                    repairValidator.validateRepairResponse(repairResponseDTO);
                })
                .collect(Collectors.toList());
        log.info("Retrieved repairs for property with id" + propertyId);
        return repairResponseDTOS;
    }

    @Override
    public List<RepairResponseDTO> getRepairsByRepairDate(LocalDate dateTime) {
        List<Repair> repairs = repairRepository.findRepairsByRepairDate(dateTime);
        return repairs.stream()
                .map(repairMapper::toRepairResponseDTO)
                .peek(repairResponseDTO -> {
                    repairValidator.checkForNull(repairResponseDTO);
                    repairValidator.validateRepairResponse(repairResponseDTO);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<RepairResponseDTO> getRepairByRangeOfDates(LocalDate startDate, LocalDate endDate) {
        if (!repairValidator.validateDateBetween(startDate, endDate)) {
            throw new InvalidParameterException("end date cannot be earlier that start");
        }
        if (startDate.equals(endDate)) {
            return getRepairsByRepairDate(startDate);
        }
        List<Repair> repairs = repairRepository
                .findAll(RepairSpecifications
                        .repairDateBetween(startDate, endDate));
        return repairs.stream()
                .filter(Repair::isActive)
                .map(repairMapper::toRepairResponseDTO)
                .peek(repairResponseDTO -> {
                    repairValidator.checkForNull(repairResponseDTO);
                    repairValidator.validateRepairResponse(repairResponseDTO);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<RepairResponseDTO> getRepairsByPropertyOwnerId(long propertyOwnerId) {
        List<PropertyDTO> propertyDTOS = propertyService.findAllPropertiesByOwnerId(propertyOwnerId);
        List<RepairResponseDTO> repairs = propertyDTOS.stream()
                .map(PropertyDTO::getId)
                .flatMap(id -> getRepairsByPropertyId(id).stream())
                .distinct()
                .peek(repairResponseDTO -> {
                    repairValidator.checkForNull(repairResponseDTO);
                    repairValidator.validateRepairResponse(repairResponseDTO);
                })
                .toList();
        return repairs;
    }

    @Override
    public BigDecimal getTotalCost(long propertyId) {
        List<RepairResponseDTO> repairs = getRepairsByPropertyId(propertyId);

        BigDecimal cost = BigDecimal.valueOf(0);
        for (RepairResponseDTO repair : repairs) {
            cost = cost.add(repair.getCostOfRepair());
        }
        return cost;
    }

    @Transactional
    @Override
    public boolean deleteRepair(long repairId) {
        Repair repair = repairRepository.findById(repairId).orElseThrow();
        if(repair.getStatusOfRepair() != StatusOfRepair.PENDING){
            log.info("Cannot delete repair due to status progress. It will be deactivated");
            repair.setActive(false);
        }
        repairRepository.delete(repair);
        log.info("Repair deleted successfully");
        return true;
    }

    @Transactional
    @Override
    public RepairResponseDTO updateRepair(long repairId, RepairUpdateDTO repairUpdateDTO) {
        Repair repair = repairRepository.findById(repairId).orElseThrow();
        repairValidator.validateRepairUpdate(repairUpdateDTO);
        if(repairUpdateDTO.getStatusOfRepair() != null){
            repair.setStatusOfRepair(repairUpdateDTO.getStatusOfRepair());
        }
        if(repairUpdateDTO.getTypeOfRepair() != null){
            repair.setTypeOfRepair(repairUpdateDTO.getTypeOfRepair());
        }
        if(repairUpdateDTO.getRepairDate() != null){
            repair.setRepairDate(repairUpdateDTO.getRepairDate());
        }
        if(repairUpdateDTO.getDescription() != null){
            repair.setDescription(repairUpdateDTO.getDescription());
        }
        repairRepository.save(repair);
        return repairMapper.toRepairResponseDTO(repair);
    }
}
