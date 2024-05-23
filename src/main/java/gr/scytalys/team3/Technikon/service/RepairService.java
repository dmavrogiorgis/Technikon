package gr.scytalys.team3.Technikon.service;
import gr.scytalys.team3.Technikon.dto.RepairCreateDTO;
import gr.scytalys.team3.Technikon.dto.RepairResponseDTO;
import gr.scytalys.team3.Technikon.dto.RepairUpdateDTO;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public interface RepairService {
    RepairResponseDTO createRepair(long propertyId, RepairCreateDTO repairCreateDTO);
    RepairResponseDTO getRepairById(long repairId);
    List<RepairResponseDTO> getRepairsByPropertyId(long propertyId);
    List<RepairResponseDTO> getRepairsByRepairDate(LocalDate date);

    List<RepairResponseDTO> getRepairByRangeOfDates(LocalDate startDate,LocalDate endDate);
    List<RepairResponseDTO> getRepairsByPropertyOwnerId(long propertyOwnerId);
    BigDecimal getTotalCost(long propertyId);
    boolean deleteRepair(long repairId);
    RepairResponseDTO updateRepair(long repairId, RepairUpdateDTO repairUpdateDTO);

//    RepairResponseDTO setCostOfRepair(RepairCreateDTO repairCreateDTO);
}
