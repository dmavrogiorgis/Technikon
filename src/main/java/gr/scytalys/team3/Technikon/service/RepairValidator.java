package gr.scytalys.team3.Technikon.service;

import gr.scytalys.team3.Technikon.dto.RepairCreateDTO;
import gr.scytalys.team3.Technikon.dto.RepairResponseDTO;
import gr.scytalys.team3.Technikon.model.Repair;

public interface RepairValidator {

    void checkForNull(RepairResponseDTO repairResponseDTO);
    void checkForNull(RepairCreateDTO repairCreateDTO);
    void validateRepairCreation(RepairCreateDTO repairCreateDTO);
    void validateRepairUpdate(RepairCreateDTO repairCreateDTO);
}
