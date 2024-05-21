package gr.scytalys.team3.Technikon.service.impl;

import gr.scytalys.team3.Technikon.dto.RepairCreateDTO;
import gr.scytalys.team3.Technikon.dto.RepairResponseDTO;
import gr.scytalys.team3.Technikon.model.Repair;
import gr.scytalys.team3.Technikon.model.StatusOfRepair;
import gr.scytalys.team3.Technikon.model.TypeOfRepair;
import gr.scytalys.team3.Technikon.service.EnumValidator;
import gr.scytalys.team3.Technikon.service.PropertyService;
import gr.scytalys.team3.Technikon.service.RepairValidator;
import lombok.AllArgsConstructor;
import org.mapstruct.ap.shaded.freemarker.template.utility.NullArgumentException;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class RepairValidatorImpl implements RepairValidator {
    private final String costRegexPattern ="^\\d+$\n";
    private final String dateformat = "^(0[1-9]|[12][0-9]|3[01])\\/(0[1-9]|1[012])\\/(19|20)\\d\\d$";
    private final EnumValidator<String> enumValidator;
    private final PropertyService propertyService;

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
    }

    @Override
    public void validateRepairCreation(RepairCreateDTO repairCreateDTO) {
        if(repairCreateDTO.getPropertyId() == 0 || propertyService.getPropertyById(repairCreateDTO.getPropertyId()) == null){
            throw new InvalidParameterException("Property must exist to create a repair.");
        }
        if(!validateTypeOfRepair(repairCreateDTO.getTypeOfRepair())){
            throw new InvalidParameterException("Type of repair is invalid");
        }
        if(!validateDateCreation(repairCreateDTO.getRepairDate())){
            throw new InvalidParameterException("Date of repair cannot be before today");
        }
        if(!validateDateFormat(repairCreateDTO.getRepairDate())){
            throw new InvalidParameterException("Format of date is wrong");
        }
        

    }

    @Override
    public void validateRepairUpdate(RepairCreateDTO repairCreateDTO) {

    }


    public boolean validateTypeOfRepair(TypeOfRepair typeOfRepair){
        return enumValidator.typeOfRepairValidate(typeOfRepair.toString());
    }
    public boolean validateStatusOfRepair(StatusOfRepair statusOfRepair){
        return enumValidator.statusOfRepairValidate(statusOfRepair.toString());
    }

    public boolean validateDateFormat(LocalDate date){
        return Pattern.matches(dateformat, date.toString());
    }
    public boolean validateDateCreation(LocalDate date){
        return !date.isBefore(LocalDate.now());
    }
    public boolean validateDateBetween(LocalDate startDate, LocalDate endDate) {
        return !startDate.isAfter(endDate);
    }
}
