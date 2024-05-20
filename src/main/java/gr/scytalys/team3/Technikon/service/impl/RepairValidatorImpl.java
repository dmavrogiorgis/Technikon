package gr.scytalys.team3.Technikon.service.impl;

import gr.scytalys.team3.Technikon.dto.RepairCreateDTO;
import gr.scytalys.team3.Technikon.dto.RepairResponseDTO;
import gr.scytalys.team3.Technikon.service.RepairValidator;
import lombok.AllArgsConstructor;
import org.mapstruct.ap.shaded.freemarker.template.utility.NullArgumentException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RepairValidatorImpl implements RepairValidator {
    private final String costRegexPattern ="^\\d+$\n";
    private final String dateformat = "^(0[1-9]|[12][0-9]|3[01])\\/(0[1-9]|1[012])\\/(19|20)\\d\\d$";

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private final LocalDate dateNow = LocalDate.now();
    @Override
    public void checkForNull(RepairResponseDTO repairResponseDTO) {
        Optional.ofNullable(repairResponseDTO).orElseThrow(() -> new NullArgumentException("The creation of the repair returned null"));
        Optional.ofNullable(repairResponseDTO.getCostOfRepair()).orElseThrow(() -> new NullArgumentException("The cost of the repair returned null"));
        Optional.ofNullable(repairResponseDTO.getTypeOfRepair()).orElseThrow(() -> new NullArgumentException("Type of repair cannot be null"));
        Optional.ofNullable(repairResponseDTO.getRepairDate()).orElseThrow(() -> new NullArgumentException("Date of repair cannot be null"));
        Optional.ofNullable(repairResponseDTO.getStatusOfRepair()).orElseThrow(() -> new NullArgumentException("Status of repair cannot be null"));
    }


    @Override
    public void checkForNull(RepairCreateDTO repairCreateDTO) {
        Optional.ofNullable(repairCreateDTO).orElseThrow(() -> new NullArgumentException("The creation of the repair returned null"));
        Optional.ofNullable(repairCreateDTO.getTypeOfRepair()).orElseThrow(() -> new NullArgumentException("Type of repair cannot be null"));
        Optional.ofNullable(repairCreateDTO.getRepairDate()).orElseThrow(() -> new NullArgumentException("Date of repair cannot be null"));
        Optional.ofNullable(repairCreateDTO.getStatusOfRepair()).orElseThrow(() -> new NullArgumentException("Status of repair cannot be null"));
    }

    @Override
    public void validateRepairCreation(RepairCreateDTO repairCreateDTO) {

    }

    @Override
    public void validateRepairUpdate(RepairCreateDTO repairCreateDTO) {

    }

    //    @Override
//    public void validateRepairCreation(RepairResponseDTO repairResponseDTO) {
//
//    }
//
//    @Override
//    public void validateRepairUpdate() {
//
//    }
}
