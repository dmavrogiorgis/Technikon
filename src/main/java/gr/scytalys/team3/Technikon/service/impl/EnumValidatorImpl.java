package gr.scytalys.team3.Technikon.service.impl;
import gr.scytalys.team3.Technikon.model.StatusOfRepair;
import gr.scytalys.team3.Technikon.model.TypeOfRepair;
import gr.scytalys.team3.Technikon.service.EnumValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class EnumValidatorImpl implements EnumValidator<String> {
    @Override
    public boolean typeOfRepairValidate(String typeOfRepair) {
       for (TypeOfRepair tr : TypeOfRepair.values()){
           if(tr.name().equals(typeOfRepair)){
               return true;
           }
       }
       return false;
    }

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
