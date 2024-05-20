package gr.scytalys.team3.Technikon.service;
import gr.scytalys.team3.Technikon.dto.RepairCreateDTO;
import gr.scytalys.team3.Technikon.dto.RepairResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public interface RepairService {
    RepairResponseDTO createRepair(RepairCreateDTO repairCreateDTO);
    RepairResponseDTO getRepairById(long repairId);
    List<RepairResponseDTO> getRepairsByPropertyId(long propertyId);
    List<RepairResponseDTO> findRepairsByRepairDate(LocalDate date);

    List<RepairResponseDTO> getRepairByRangeOfDates(LocalDate startDate, LocalDate endDate);
    List<RepairResponseDTO> getRepairsByPropertyOwnerId(long propertyOwnerId);
    BigDecimal getTotalCost(long propertyId);
    boolean deleteRepair(long repairId);
    RepairResponseDTO updateRepair(long repairId, RepairResponseDTO repairResponseDTO);

//    RepairResponseDTO setCostOfRepair(RepairCreateDTO repairCreateDTO);
}
