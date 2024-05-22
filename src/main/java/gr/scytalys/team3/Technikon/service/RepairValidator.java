package gr.scytalys.team3.Technikon.service;

import gr.scytalys.team3.Technikon.dto.RepairCreateDTO;
import gr.scytalys.team3.Technikon.dto.RepairResponseDTO;
import gr.scytalys.team3.Technikon.dto.RepairUpdateDTO;
import gr.scytalys.team3.Technikon.model.Repair;
import gr.scytalys.team3.Technikon.model.TypeOfRepair;

import java.time.LocalDate;

public interface RepairValidator {

    void checkForNull(RepairResponseDTO repairResponseDTO);
    void checkForNull(RepairCreateDTO repairCreateDTO);
    void validateRepairCreation(RepairCreateDTO repairCreateDTO);
    void validateRepairUpdate(RepairUpdateDTO repairUpdateDTO);
    void validateRepairResponse(RepairResponseDTO repairResponseDTO);

    public boolean validateTypeOfRepair(TypeOfRepair typeOfRepair);
    public boolean validateDateCreation(LocalDate date);
    public boolean validateDateBetween(LocalDate startDate, LocalDate endDate);
}
