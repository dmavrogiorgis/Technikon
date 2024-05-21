package gr.scytalys.team3.Technikon.service;
import gr.scytalys.team3.Technikon.dto.RepairCreateDTO;
import gr.scytalys.team3.Technikon.dto.RepairResponseDTO;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public interface RepairService {
    RepairResponseDTO createRepair(RepairCreateDTO repairCreateDTO);
    RepairResponseDTO getRepairById(long repairId);
    List<RepairResponseDTO> getRepairsByPropertyId(long propertyId);
    List<RepairResponseDTO> findRepairsByRepairDate(@DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate date);

    List<RepairResponseDTO> getRepairByRangeOfDates(@DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate startDate,@DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate endDate);
    List<RepairResponseDTO> getRepairsByPropertyOwnerId(long propertyOwnerId);
    BigDecimal getTotalCost(long propertyId);
    boolean deleteRepair(long repairId);
    RepairResponseDTO updateRepair(long repairId, RepairResponseDTO repairResponseDTO);

//    RepairResponseDTO setCostOfRepair(RepairCreateDTO repairCreateDTO);
}
