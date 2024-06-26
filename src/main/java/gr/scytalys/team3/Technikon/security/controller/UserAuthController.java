package gr.scytalys.team3.Technikon.security.controller;

import gr.scytalys.team3.Technikon.dto.LoginResponseDTO;
import gr.scytalys.team3.Technikon.dto.PropertyOwnerDTO;
import gr.scytalys.team3.Technikon.dto.PropertyOwnerResponseDTO;
import gr.scytalys.team3.Technikon.exception.ApiExceptionHandler;
import gr.scytalys.team3.Technikon.security.dto.AuthDTO;
import gr.scytalys.team3.Technikon.security.service.JwtService;
import gr.scytalys.team3.Technikon.security.service.UserInfoDetails;
import gr.scytalys.team3.Technikon.security.service.UserInfoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@AllArgsConstructor

public class UserAuthController {
    private final UserInfoService service;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
    @PostMapping("/signup")
    public ResponseEntity<PropertyOwnerResponseDTO> createPropertyOwner(@RequestBody PropertyOwnerDTO propertyOwnerDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Property Owner registered successfully!");
        return new ResponseEntity<>(service.createPropertyOwner(propertyOwnerDTO),
                                    headers,
                                    HttpStatus.CREATED);
    }
    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> authenticateAndGetToken(@RequestBody AuthDTO authDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDTO.getUsername(), authDTO.getPassword()));
        if (authentication.isAuthenticated()) {
            UserInfoDetails userInfoDetails = service.loadUserByUsername(authDTO.getUsername());
            String token = jwtService.generateToken(authDTO.getUsername(),
                                                    userInfoDetails.getUsername(),
                                                    userInfoDetails.getEmail(),
                                                    userInfoDetails.getId());
            HttpHeaders headers = new HttpHeaders();
            headers.add("message", "User logged in successfully!");
            return new ResponseEntity<>(new LoginResponseDTO(userInfoDetails.getId(),
                                                             userInfoDetails.getUsername(),
                                                             userInfoDetails.getTin(),
                                                             userInfoDetails.getEmail(),
                                                             token,
                                                             userInfoDetails.getAuthorities()),
                                        headers,
                                        HttpStatus.OK);
        } else {
            throw new BadCredentialsException("Invalid credentials for user:" + authDTO.getUsername());
        }
    }
}
