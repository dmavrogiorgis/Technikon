package gr.scytalys.team3.Technikon.exception;

import gr.scytalys.team3.Technikon.dto.PropertyOwnerResponseDTO;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.ap.shaded.freemarker.template.utility.NullArgumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.InvalidParameterException;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(NullArgumentException.class)
    public ResponseEntity<PropertyOwnerResponseDTO> handleNullArgumentException(NullArgumentException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", ex.getMessage());
        return new ResponseEntity<>(null, headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<PropertyOwnerResponseDTO> handleInvalidParameterException(InvalidParameterException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", ex.getMessage());
        return new ResponseEntity<>(null, headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<PropertyOwnerResponseDTO> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        logger.error("Data integrity violation", ex);
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", ex.getMessage());
        return new ResponseEntity<>(null, headers, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<PropertyOwnerResponseDTO> handleEntityNotFoundException(EntityNotFoundException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", ex.getMessage());
        return new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<PropertyOwnerResponseDTO> handleAccessDeniedException(AccessDeniedException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", ex.getMessage());
        return new ResponseEntity<>(null, headers, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<PropertyOwnerResponseDTO> handleBadCredentialsException(BadCredentialsException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", ex.getMessage());
        return new ResponseEntity<>(null, headers, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<PropertyOwnerResponseDTO> handleExpiredJwtException(ExpiredJwtException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", ex.getMessage());
        return new ResponseEntity<>(null, headers, HttpStatus.UNAUTHORIZED);
    }
}
