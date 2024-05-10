package gr.scytalys.team3.Technikon.controller;

import gr.scytalys.team3.Technikon.dto.PropertyOwnerDTO;
import gr.scytalys.team3.Technikon.service.PropertyOwnerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/owner")
@AllArgsConstructor
public class PropertyOwnerController {

    private final PropertyOwnerService propertyOwnerService;

    @PostMapping("")
    public ResponseEntity<PropertyOwnerDTO> createPropertyOwner(@RequestBody PropertyOwnerDTO propertyOwnerDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Property Owner created!");
        return new ResponseEntity<>(propertyOwnerService.createPropertyOwner(propertyOwnerDTO), headers, HttpStatus.CREATED);
    }
}
