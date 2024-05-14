package gr.scytalys.team3.Technikon.controller;

import gr.scytalys.team3.Technikon.dto.PropertyOwnerDTO;
import gr.scytalys.team3.Technikon.dto.PropertyOwnerSearchDTO;
import gr.scytalys.team3.Technikon.model.PropertyOwnerUpdateDTO;
import gr.scytalys.team3.Technikon.service.PropertyOwnerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("api/owner")
public class PropertyOwnerController {

    private final PropertyOwnerService propertyOwnerService;

    @PostMapping("")
    public ResponseEntity<PropertyOwnerDTO> createPropertyOwner(@RequestBody PropertyOwnerDTO propertyOwnerDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Property Owner created!");
        return new ResponseEntity<>(propertyOwnerService.createPropertyOwner(propertyOwnerDTO),
                                    headers,
                                    HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public ResponseEntity<PropertyOwnerDTO> searchPropertyOwner(@RequestParam(required = false) String tin,
                                                                @RequestParam(required = false) String email,
                                                                @RequestParam(required = false) String username) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Property Owner found!");
        return new ResponseEntity<>(propertyOwnerService.searchPropertyOwner(new PropertyOwnerSearchDTO(tin, email, username)),
                                    headers,
                                    HttpStatus.OK);
    }

    @PutMapping("/update/{propertyOwnerTIN}")
    public ResponseEntity<PropertyOwnerDTO> searchPropertyOwner(@PathVariable String propertyOwnerTIN,
                                                                @RequestBody PropertyOwnerUpdateDTO propertyOwnerUpdateDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Property Owner updated successfully!");
        propertyOwnerService.updatePropertyOwner(propertyOwnerTIN, propertyOwnerUpdateDTO);
        return new ResponseEntity<>(null, headers, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{propertyOwnerTIN}")
    public ResponseEntity<PropertyOwnerDTO> searchPropertyOwner(@PathVariable String propertyOwnerTIN) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Property Owner deleted successfully!");
        propertyOwnerService.deletePropertyOwner(propertyOwnerTIN);
        return new ResponseEntity<>(null, headers, HttpStatus.OK);
    }
}
