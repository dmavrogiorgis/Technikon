package gr.scytalys.team3.Technikon.service.impl;

import gr.scytalys.team3.Technikon.dto.PropertyOwnerDTO;
import gr.scytalys.team3.Technikon.dto.ResponseApi;
import gr.scytalys.team3.Technikon.mapper.PropertyOwnerMapper;
import gr.scytalys.team3.Technikon.model.Property;
import gr.scytalys.team3.Technikon.service.PropertyOwnerService;
import gr.scytalys.team3.Technikon.service.PropertyOwnerValidator;
import gr.scytalys.team3.Technikon.service.PropertyValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor

public class PropertyValidatorImpl implements PropertyValidator {
    private static final String tinRegexPattern = "^[0-9]{9}$";
    private final PropertyOwnerValidator propertyOwnerValidator;
    private final PropertyOwnerService propertyOwnerService;
    private final PropertyOwnerMapper propertyOwnerMapper;


    @Override
    public void validate(Property property) {

        if (property == null){
            throw new InvalidParameterException("null property provided", null);
        }
        if (property.getPropertyOwner() == null){
            throw new InvalidParameterException("null property owner provided", null);
        }

        if (!isPropertyINValid(property.getPropertyIN()) )
        {
            throw new InvalidParameterException("invalid E9 provided "+property.getPropertyIN());
        }
        if (property.getYearOfConstruct()<1800 || property.getYearOfConstruct()>2024)
        {
            throw new InvalidParameterException("invalid year of construction "+property.getYearOfConstruct());
        }
    }
    public boolean isPropertyINValid(long tin){
        return Pattern.matches(tinRegexPattern, Long.toString(tin));
    }

}
