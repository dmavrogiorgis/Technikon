package gr.scytalys.team3.Technikon.service;

import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.regex.Pattern;

@Service
public class PropertyOwnerValidatorImpl implements PropertyOwnerValidator {

    private static final String tinRegexPattern = "^[0-9]{9}$";
    private static final String emailRegexPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final String phoneNumberRegexPattern = "^69[0-9]{8}$";

    @Override
    public void validatePropertyOwner(String tin, String email, String phoneNumber) {

        if (!isTINValid(tin)) {
            throw new InvalidParameterException("Invalid TIN: " + tin);
        }

        if (email != null && !isEmailValid(email)) {
            throw new InvalidParameterException("Invalid Email format: " + email);
        }

        if (phoneNumber != null && !isPhoneNumberValid(phoneNumber)) {
            throw new InvalidParameterException("Invalid Phone number: " + phoneNumber);
        }
    }

    @Override
    public boolean isTINValid(String tin){
        return Pattern.matches(tinRegexPattern, tin.trim());
    }

    public boolean isEmailValid(String email){
        return Pattern.matches(emailRegexPattern, email.trim());
    }

    public boolean isPhoneNumberValid(String phoneNumber){
        return Pattern.matches(phoneNumberRegexPattern, phoneNumber.trim());
    }
}
