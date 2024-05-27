package gr.scytalys.team3.Technikon.service;

import gr.scytalys.team3.Technikon.dto.RepairCreateDTO;
import gr.scytalys.team3.Technikon.dto.RepairResponseDTO;
import gr.scytalys.team3.Technikon.dto.RepairUpdateDTO;
import gr.scytalys.team3.Technikon.model.Property;
import gr.scytalys.team3.Technikon.model.Repair;
import gr.scytalys.team3.Technikon.model.TypeOfRepair;

import java.time.LocalDate;
/**
 * Interface for validating repair-related data.
 */
public interface RepairValidator {
    /**
     * Checks for null values in a RepairResponseDTO.
     *
     * @param repairResponseDTO  The RepairResponseDTO to validate.
     */
    void checkForNull(RepairResponseDTO repairResponseDTO);
    /**
     * Checks for null values in a RepairCreateDTO.
     *
     * @param repairCreateDTO  The RepairCreateDTO to validate.
     */
    void checkForNull(RepairCreateDTO repairCreateDTO);
    /**
     * Validates data for creating a repair.
     *
     * @param repairCreateDTO  The RepairCreateDTO to validate.
     */
    void validateRepairCreation(RepairCreateDTO repairCreateDTO);
    /**
     * Validates data for updating a repair.
     *
     * @param repairUpdateDTO  The RepairUpdateDTO to validate.
     */
    void validateRepairUpdate(RepairUpdateDTO repairUpdateDTO);
    /**
     * Validates data in a RepairResponseDTO.
     *
     * @param repairResponseDTO  The RepairResponseDTO to validate.
     */
    void validateRepairResponse(RepairResponseDTO repairResponseDTO);
    /**
     * Validates a type of repair.
     *
     * @param typeOfRepair  The type of repair to validate.
     * @return True if the type of repair is valid, false otherwise.
     */
    public boolean validateTypeOfRepair(TypeOfRepair typeOfRepair);
    /**
     * Validates a repair creation date.
     *
     * @param date  The repair creation date to validate.
     * @return True if the date is valid, false otherwise.
     */
    public boolean validateDateCreation(LocalDate date);
    /**
     * Validates that a start date is before or equal to an end date.
     *
     * @param startDate  The start date.
     * @param endDate    The end date.
     * @return True if the date range is valid, false otherwise.
     */
    public boolean validateDateBetween(LocalDate startDate, LocalDate endDate);

    public boolean validateProperty(RepairResponseDTO repairResponseDTO);
}
