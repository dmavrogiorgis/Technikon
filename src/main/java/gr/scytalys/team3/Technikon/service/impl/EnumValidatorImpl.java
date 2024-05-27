package gr.scytalys.team3.Technikon.service.impl;
import gr.scytalys.team3.Technikon.model.StatusOfRepair;
import gr.scytalys.team3.Technikon.model.TypeOfRepair;
import gr.scytalys.team3.Technikon.service.EnumValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

/**
 * Implementation of an enum validator for repair-related enums.
 */
@Service
@Slf4j
public class EnumValidatorImpl implements EnumValidator<String> {
    /**
     * Validates a given type of repair.
     *
     * @param typeOfRepair  The type of repair to validate.
     * @return True if the type of repair is valid, false otherwise.
     */
    @Override
    public boolean typeOfRepairValidate(String typeOfRepair) {
       for (TypeOfRepair tr : TypeOfRepair.values()){
           if(tr.name().equals(typeOfRepair)){
               return true;
           }
       }
       return false;
    }
    /**
     * Validates a given status of repair.
     *
     * @param statusOfRepair  The status of repair to validate.
     * @return True if the status of repair is valid, false otherwise.
     */
    @Override
    public boolean statusOfRepairValidate(String statusOfRepair) {
        for (StatusOfRepair sr : StatusOfRepair.values()){
            if(sr.name().equals(statusOfRepair)){
                return true;
            }
        }
        return false;
    }
}
