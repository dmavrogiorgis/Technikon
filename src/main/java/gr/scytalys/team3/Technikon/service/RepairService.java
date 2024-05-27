package gr.scytalys.team3.Technikon.service;
import gr.scytalys.team3.Technikon.dto.RepairCreateDTO;
import gr.scytalys.team3.Technikon.dto.RepairResponseDTO;
import gr.scytalys.team3.Technikon.dto.RepairUpdateDTO;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * The interface of the repair service, defines all the CRUD methods
 * that will be implemented, and also more specific search options based
 * on propertyOwner and dateOfRepair.
 */

public interface RepairService {
    /**
     * Creates a new repair for a given property.
     *
     * @param propertyId       The ID of the property that the repair is made for.
     * @param repairCreateDTO  The DTO containing information for creating the repair.
     * @return A RepairResponseDTO with details of the newly created repair.
     */
    RepairResponseDTO createRepair(long propertyId, RepairCreateDTO repairCreateDTO);
    /**
     * Retrieves a repair by its unique ID.
     *
     * @param repairId  The ID of the repair to retrieve.
     * @return A RepairResponseDTO with details of the specified repair.
     */
    RepairResponseDTO getRepairById(long repairId);
    /**
     * Retrieves all repairs associated with a specific property.
     *
     * @param propertyId  The ID of the property.
     * @return A list of RepairResponseDTO objects representing repairs for the property.
     */
    List<RepairResponseDTO> getRepairsByPropertyId(long propertyId);
    /**
     * Retrieves all repairs for a specific repair date.
     *
     * @param date  The repair date.
     * @return A list of RepairResponseDTO objects for the specified date.
     */
    List<RepairResponseDTO> getRepairsByRepairDate(LocalDate date);
    /**
     * Retrieves repairs within a specified date range.
     *
     * @param startDate  The start date of the range.
     * @param endDate    The end date of the range.
     * @return A list of RepairResponseDTO objects within the specified date range.
     */
    List<RepairResponseDTO> getRepairByRangeOfDates(LocalDate startDate,LocalDate endDate);
    /**
     * Retrieves all repairs associated with a specific property owner.
     *
     * @param propertyOwnerId  The ID of the property owner.
     * @return A list of RepairResponseDTO objects for the property owner.
     */
    List<RepairResponseDTO> getRepairsByPropertyOwnerId(long propertyOwnerId);
    /**
     * Calculates the total cost of repairs for a specific property.
     *
     * @param propertyId  The ID of the property.
     * @return The total cost as a BigDecimal.
     */
    BigDecimal getTotalCost(long propertyId);
    /**
     * Deletes a repair by its unique ID, or deactivates it.
     *
     * @param repairId  The ID of the repair to delete.
     * @return True if the repair was successfully deleted or deactivated, false otherwise.
     */
    boolean deleteRepair(long repairId);
    /**
     * Updates an existing repair.
     *
     * @param repairId          The ID of the repair to update.
     * @param repairUpdateDTO   The DTO containing updated information for the repair.
     * @return A RepairResponseDTO with details of the updated repair.
     */
    RepairResponseDTO updateRepair(long repairId, RepairUpdateDTO repairUpdateDTO);
}
