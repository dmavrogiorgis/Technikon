package gr.scytalys.team3.Technikon.service;
import gr.scytalys.team3.Technikon.dto.RepairDTO;
import gr.scytalys.team3.Technikon.model.Repair;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public interface RepairService {
    RepairDTO createRepair(RepairDTO repairDTO);
    RepairDTO getRepairById(long repairId);
    List<RepairDTO> getRepairsByPropertyId(long propertyId);
    List<RepairDTO> findRepairsByRepairDate(LocalDate date);

    List<RepairDTO> getRepairByRangeOfDates(LocalDate startDate, LocalDate endDate);
    List<RepairDTO> getRepairsByPropertyOwnerId(long propertyOwnerId);
    BigDecimal getTotalCost(long propertyId);
    boolean deleteRepair(long repairId);
    RepairDTO updateRepair(long repairId, RepairDTO repairDTO);
}
