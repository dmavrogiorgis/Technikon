package gr.scytalys.team3.Technikon.controller;

import gr.scytalys.team3.Technikon.model.PropertyOwner;
import org.mapstruct.ap.shaded.freemarker.template.utility.NullArgumentException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.InvalidParameterException;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(NullArgumentException.class)
    public ResponseEntity<PropertyOwner> handleNullArgumentException(NullArgumentException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", ex.getMessage());
        return new ResponseEntity<>(null, headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<PropertyOwner> handleInvalidParameterException(InvalidParameterException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", ex.getMessage());
        return new ResponseEntity<>(null, headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<PropertyOwner> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", ex.getMessage());
        return new ResponseEntity<>(null, headers, HttpStatus.CONFLICT);
    }
}
