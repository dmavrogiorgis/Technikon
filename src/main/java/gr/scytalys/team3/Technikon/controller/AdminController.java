package gr.scytalys.team3.Technikon.controller;

import gr.scytalys.team3.Technikon.dto.PropertyDTO;
import gr.scytalys.team3.Technikon.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class AdminController {

    private final PropertyService propertyService;

    @GetMapping("/property/all")
    public ResponseEntity<List<PropertyDTO>> getAllProperties(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "All Registered Properties found!");
        return new ResponseEntity<>(propertyService.getAllProperties(), headers, HttpStatus.FOUND);
    }

    @GetMapping("/property/active")
    public ResponseEntity<List<PropertyDTO>> getAllActiveProperties(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "All Registered Properties found!");
        return new ResponseEntity<>(propertyService.getAllActiveProperties(), headers, HttpStatus.FOUND);
    }

}
