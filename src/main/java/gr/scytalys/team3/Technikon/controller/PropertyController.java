package gr.scytalys.team3.Technikon.controller;

import gr.scytalys.team3.Technikon.dto.PropertyDTO;
import gr.scytalys.team3.Technikon.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owner/")
@CrossOrigin(origins = "http://localhost:5174", allowCredentials = "true")
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class PropertyController {
    private final PropertyService propertyService;

    @PostMapping("{ownerId}/property")
    public ResponseEntity<PropertyDTO> createProperty(@PathVariable long ownerId,@RequestBody PropertyDTO propertyDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Property created!");
        return new ResponseEntity<>(propertyService
                .createProperty(ownerId, propertyDTO), headers, HttpStatus.CREATED);
    }

    @GetMapping("{ownerId}/property/{propertyId}")
    public ResponseEntity<PropertyDTO> getProperty(@PathVariable Long propertyId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Property found!");
        return new ResponseEntity<>(propertyService
                .getPropertyById(propertyId), headers, HttpStatus.OK);
    }

    @GetMapping("{ownerId}/property")
    public ResponseEntity <List<PropertyDTO>> getPropertyByOwnerId(@PathVariable long ownerId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Property found!");
        return new ResponseEntity<>(propertyService.findAllPropertiesByOwnerId(ownerId), headers, HttpStatus.OK);
    }

    @PutMapping("{ownerId}/property/{propertyId}")
    public ResponseEntity<PropertyDTO> updateProperty(@RequestBody PropertyDTO propertyDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Property updated!");
        return new ResponseEntity<>(propertyService
                .updateProperty(propertyDTO), headers, HttpStatus.OK);
    }
    @DeleteMapping("{ownerId}/property/{propertyId}")
    public ResponseEntity<String> deleteProperty(@PathVariable long propertyId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("message",propertyService.deleteOrDeactivatePropertyById(propertyId));
        return new ResponseEntity<>(propertyService
                .deleteOrDeactivatePropertyById(propertyId), headers, HttpStatus.OK);
    }

}
