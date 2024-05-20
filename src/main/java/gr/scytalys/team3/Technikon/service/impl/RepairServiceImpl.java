package gr.scytalys.team3.Technikon.service.impl;

import gr.scytalys.team3.Technikon.dto.RepairDTO;
import gr.scytalys.team3.Technikon.mapper.RepairMapper;
import gr.scytalys.team3.Technikon.model.Property;
import gr.scytalys.team3.Technikon.model.PropertyOwner;
import gr.scytalys.team3.Technikon.model.Repair;
import gr.scytalys.team3.Technikon.repository.PropertyOwnerRepository;
import gr.scytalys.team3.Technikon.repository.PropertyRepository;
import gr.scytalys.team3.Technikon.repository.RepairRepository;
import gr.scytalys.team3.Technikon.repository.specifications.RepairSpecifications;
import gr.scytalys.team3.Technikon.service.RepairService;
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
    private PropertyRepository propertyRepository;
    private PropertyOwnerRepository propertyOwnerRepository;
    private RepairMapper repairMapper;


    @Override
    @Transactional
    public RepairDTO createRepair(RepairDTO repairDTO) {
        if(propertyRepository.findById(repairDTO.getPropertyId()).isEmpty())
        {
            log.debug("property not found");
            throw new RuntimeException("Property with id " + repairDTO.getPropertyId() + " was not found in db");
        }
        Repair repair =  repairMapper.repair(repairDTO);
        return repairMapper.repairDTO(repairRepository.save(repair));
    }

    @Override
    public RepairDTO getRepairById(long repairId) {
        RepairDTO repair = repairMapper.repairDTO(repairRepository.findById(repairId).orElse(null));
        if(repair == null){
            log.debug("Repair with id" + repairId + "has not been found");
            return repair;
        }
        log.info("Retrieved Repair with id: " + repairId);
        return repair;
    }

    @Override
    public List<RepairDTO> getRepairsByPropertyId(long propertyId) {
        Optional<Property> property = propertyRepository.findById(propertyId);
        if (property.isEmpty()) {
            log.debug("Could not retrieve property with id: " + propertyId);
            throw new EntityNotFoundException("Could not retrieve property with id: " + propertyId);
        }
        log.info("Repairs for property with id: " + propertyId + "been retrieved");
        List<RepairDTO> repairs = new ArrayList<>();
        for(Repair item : property.get().getRepairs()){
            RepairDTO repair = repairMapper.repairDTO(item);
            repairs.add(repair);
        }
        if(repairs.isEmpty()){
            log.debug("There are not repairs for property with: " + propertyId);
        }
        return repairs;
    }

    @Override
    public List<RepairDTO> findRepairsByRepairDate(LocalDate dateTime) {
        List<Repair> repairs = repairRepository.findRepairsByRepairDate(dateTime);
        if(repairs.isEmpty()){
            log.debug("The are no repairs in the specific date to retrieve");
            throw new EntityNotFoundException("No repairs found");
        }
        return repairs.
                stream().
                map(repairMapper::repairDTO).collect(Collectors.toList());
    }

    @Override
    public List<RepairDTO> getRepairByRangeOfDates(LocalDate startDate, LocalDate endDate) {
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
        List<Repair> repairs = repairRepository.
                                    findAll(
                                            RepairSpecifications.
                                                    repairDateBetween(startDate, endDate)
                                    );
        return repairs.stream().map(repairMapper::repairDTO).collect(Collectors.toList());
    }

    @Override
    public List<RepairDTO> getRepairsByPropertyOwnerId(long propertyOwnerId) {
        PropertyOwner propertyOwner = propertyOwnerRepository.findById(propertyOwnerId).orElse(null);
        if(propertyOwner == null){
            log.debug("PropertyOwner with id: " + propertyOwnerId + "was not found");
            throw new RuntimeException("PropertyOwner with id" + propertyOwnerId + "was not found");
        }
        List<Property> properties = propertyRepository.findPropertiesByPropertyOwner(propertyOwner).stream().toList();
        if(properties.isEmpty()){
            log.debug("No properties where found for propertyOwner" + propertyOwner.getId());
            throw new RuntimeException("No properties where found for propertyOwner" + propertyOwner.getId());
        }
        List<RepairDTO> repairs = new ArrayList<>();
        for(Property property : properties){
            repairs.addAll(getRepairsByPropertyId(property.getPropertyId()));
        }
        if(repairs.isEmpty()){
            log.debug("No repairs for the List of properties");
            throw new RuntimeException("No repairs for the List of properties ");
        }
        return  repairs;
    }
//
    @Override
    public BigDecimal getTotalCost(long propertyId) {
        List<RepairDTO> repairs = getRepairsByPropertyId(propertyId);
        BigDecimal cost = BigDecimal.valueOf(0);
        for(RepairDTO repair : repairs){
            cost = cost.add(repair.getCostOfRepair());
        }
        return cost;
    }

    @Transactional
    public boolean deleteRepair(long repairId){
        boolean isDeleted = false;
        Repair repair = repairRepository.findById(repairId).orElse(null);
        if(repair == null) {
            log.debug(STR."Repair with id \{repairId} cannot be retrieved");
            throw new RuntimeException(STR."No repairs found with repairId\{repairId}");
        }
        if(!repair.getStatusOfRepair().toString().equals("PENDING")){
            log.debug("Repair deactivated due to status progress");
            repair.setActive(false);
            return isDeleted;
        }
        repairRepository.delete(repair);
        log.debug(STR."Deleted repair with id: \{repairId}");
        isDeleted = true;
        return isDeleted;
    }

    @Override
    public RepairDTO updateRepair(long repairId, RepairDTO repairDTO) {
        Repair repairDB = repairRepository.findById(repairId).orElse(null);
        if(repairDB == null){
            log.debug("Could not retrieve repaid with id: " + repairId);
            throw new RuntimeException(STR."Could not retrieve repaid with id: " + repairId);
        }
        if(repairDTO.getStatusOfRepair() != null){
            repairDB.setStatusOfRepair(repairDTO.getStatusOfRepair());
        }
        if(repairDTO.getTypeOfRepair() != null){
            repairDB.setTypeOfRepair(repairDTO.getTypeOfRepair());
        }
        if(repairDTO.getCostOfRepair() != null){
            repairDB.setCostOfRepair(repairDTO.getCostOfRepair());
        }
        if(repairDTO.getRepairDate() != null){
            repairDB.setRepairDate(repairDTO.getRepairDate());
        }
        if(repairDTO.getDescription() != null){
            repairDB.setDescription(repairDTO.getDescription());
        }
        if(Long.valueOf(repairId) != null){
            Optional<Property>  property = propertyRepository.findById(repairDTO.getPropertyId());
            repairDB.setProperty(property.orElse(null));
        }
        repairRepository.save(repairDB);
        return repairMapper.repairDTO(repairDB);
    }
}
