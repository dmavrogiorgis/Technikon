package gr.scytalys.team3.Technikon.service.impl;

import gr.scytalys.team3.Technikon.dto.PropertyOwnerDTO;
import gr.scytalys.team3.Technikon.service.PropertyOwnerValidator;
import org.mapstruct.ap.shaded.freemarker.template.utility.NullArgumentException;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class PropertyOwnerValidatorImpl implements PropertyOwnerValidator {

    private static final String tinRegexPattern = "^[0-9]{9}$";
    private static final String emailRegexPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final String phoneNumberRegexPattern = "^69[0-9]{8}$";

    @Override
    public void checkForNullCreation(PropertyOwnerDTO propertyOwnerDTO) {
        Optional.ofNullable(propertyOwnerDTO)
                .orElseThrow(() -> new NullArgumentException("Property Owner to create is null!"));
        Optional.ofNullable(propertyOwnerDTO.getTin())
                .orElseThrow(() -> new InvalidParameterException("TIN is null!"));
        Optional.ofNullable(propertyOwnerDTO.getName())
                .orElseThrow(() -> new InvalidParameterException("Name is null!"));
        Optional.ofNullable(propertyOwnerDTO.getSurname())
                .orElseThrow(() -> new InvalidParameterException("Surname is null!"));
        Optional.ofNullable(propertyOwnerDTO.getAddress())
                .orElseThrow(() -> new InvalidParameterException("Address is null!"));
        Optional.ofNullable(propertyOwnerDTO.getPhoneNumber())
                .orElseThrow(() -> new InvalidParameterException("Phone number is null!"));
        Optional.ofNullable(propertyOwnerDTO.getEmail())
                .orElseThrow(() -> new InvalidParameterException("Email is null!"));
        Optional.ofNullable(propertyOwnerDTO.getUsername())
                .orElseThrow(() -> new InvalidParameterException("Username is null!"));
        Optional.ofNullable(propertyOwnerDTO.getPassword())
                .orElseThrow(() -> new InvalidParameterException("Password is null!"));
    }

    @Override
    public void validatePropertyOwnerCreation(PropertyOwnerDTO propertyOwnerDTO) {

        if (propertyOwnerDTO.getTin() != null && !isTINValid(propertyOwnerDTO.getTin())) {
            throw new InvalidParameterException("Invalid TIN: " + propertyOwnerDTO.getTin());
        }

        if (propertyOwnerDTO.getEmail()!= null && !isEmailValid(propertyOwnerDTO.getEmail())) {
            throw new InvalidParameterException("Invalid Email format: " + propertyOwnerDTO.getEmail());
        }

        if (propertyOwnerDTO.getPhoneNumber() != null && !isPhoneNumberValid(propertyOwnerDTO.getPhoneNumber())) {
            throw new InvalidParameterException("Invalid Phone number: " + propertyOwnerDTO.getPhoneNumber());
        }

        //name/surname/username at least 2 characters
        Optional.ofNullable(propertyOwnerDTO.getName())
                .filter(name -> name.length() >= 2)
                .orElseThrow(() -> new InvalidParameterException("Name should be at least 2 characters!"));
        Optional.ofNullable(propertyOwnerDTO.getSurname())
                .filter(surname -> surname.length() >= 2)
                .orElseThrow(() -> new InvalidParameterException("Surname should be at least 2 characters!"));
        Optional.ofNullable(propertyOwnerDTO.getUsername())
                .filter(username -> username.length() >= 2)
                .orElseThrow(() -> new InvalidParameterException("Username should be at least 2 characters!"));
    }

    @Override
    public void validatePropertyOwnerUpdate(String email) {
        if (email != null && !isEmailValid(email)) {
            throw new InvalidParameterException("Invalid Email format: " + email);
        }
    }

    public boolean isTINValid(String tin){
        return Pattern.matches(tinRegexPattern, tin.trim());
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean isEmailValid(String email){
        return Pattern.matches(emailRegexPattern, email.trim());
    }

    public boolean isPhoneNumberValid(String phoneNumber){
        return Pattern.matches(phoneNumberRegexPattern, phoneNumber.trim());
    }
}
