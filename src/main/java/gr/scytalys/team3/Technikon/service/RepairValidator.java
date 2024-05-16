package gr.scytalys.team3.Technikon.service;

import gr.scytalys.team3.Technikon.dto.RepairDTO;

public interface RepairValidator {

    void checkForNull(RepairDTO repairDTO);
    void validateRepairCreation(RepairDTO repairDTO);
    void validateRepairUpdate();
}
