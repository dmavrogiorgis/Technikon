package gr.scytalys.team3.Technikon.controller;

import gr.scytalys.team3.Technikon.dto.PropertyOwnerResponseDTO;
import gr.scytalys.team3.Technikon.dto.PropertyOwnerSearchDTO;
import gr.scytalys.team3.Technikon.dto.PropertyOwnerUpdateDTO;
import gr.scytalys.team3.Technikon.security.service.UserInfoDetails;
import gr.scytalys.team3.Technikon.service.PropertyOwnerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/owner")
public class PropertyOwnerController {

    private final PropertyOwnerService propertyOwnerService;

    @GetMapping("/search")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
    public ResponseEntity<PropertyOwnerResponseDTO> searchPropertyOwner(@RequestParam(required = false) String tin,
                                                                        @RequestParam(required = false) String email,
                                                                        @RequestParam(required = false) String username,
                                                                        Authentication authentication) {
        if (authentication.getAuthorities().stream().noneMatch(sga -> sga.getAuthority().equals("ROLE_ADMIN"))) {
            UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
            if (!userInfoDetails.getTin().equals(tin)){
                throw new AccessDeniedException("Unauthorized to search property owner with TIN: " + tin);
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "User found!");
        return new ResponseEntity<>(propertyOwnerService.searchPropertyOwner(new PropertyOwnerSearchDTO(tin, email, username)),
                                    headers,
                                    HttpStatus.OK);
    }

    @PutMapping("/update/{propertyOwnerTIN}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
    public ResponseEntity<PropertyOwnerResponseDTO> updatePropertyOwner(@PathVariable String propertyOwnerTIN,
                                                                        @RequestBody PropertyOwnerUpdateDTO propertyOwnerUpdateDTO,
                                                                        Authentication authentication) {
        if (authentication.getAuthorities().stream().noneMatch(sga -> sga.getAuthority().equals("ROLE_ADMIN"))) {
            UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
            if (!userInfoDetails.getTin().equals(propertyOwnerTIN)){
                throw new AccessDeniedException("Unauthorized to update property owner with TIN: " + propertyOwnerTIN);
            }
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Property Owner updated successfully!");
        propertyOwnerService.updatePropertyOwner(propertyOwnerTIN, propertyOwnerUpdateDTO);
        return new ResponseEntity<>( headers, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{propertyOwnerTIN}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
    public ResponseEntity<PropertyOwnerSearchDTO> deletePropertyOwner(@PathVariable String propertyOwnerTIN,
                                                                      Authentication authentication) {
        if (authentication.getAuthorities().stream().noneMatch(sga -> sga.getAuthority().equals("ROLE_ADMIN"))) {
            UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
            if (!userInfoDetails.getTin().equals(propertyOwnerTIN)){
                throw new AccessDeniedException("Unauthorized to delete property owner with TIN: " + propertyOwnerTIN);
            }
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Property Owner deleted successfully!");
        propertyOwnerService.deletePropertyOwner(propertyOwnerTIN);
        return new ResponseEntity<>(null, headers, HttpStatus.OK);
    }
}
