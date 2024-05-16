package gr.scytalys.team3.Technikon.service;

import gr.scytalys.team3.Technikon.dto.RepairDTO;

public class RepairValidatorImpl implements RepairValidator{
    private final String costRegexPattern ="^\\d+$\n";
    private final String dateformat = "^(0[1-9]|[12][0-9]|3[01])\\/(0[1-9]|1[012])\\/(19|20)\\d\\d$";
    @Override
    public void checkForNull(RepairDTO repairDTO) {

    }

    @Override
    public void validateRepairCreation(RepairDTO repairDTO) {

    }

    @Override
    public void validateRepairUpdate() {

    }
}
