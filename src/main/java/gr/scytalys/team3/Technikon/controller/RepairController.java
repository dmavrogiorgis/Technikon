package gr.scytalys.team3.Technikon.controller;

import gr.scytalys.team3.Technikon.dto.PropertyDTO;
import gr.scytalys.team3.Technikon.dto.RepairCreateDTO;
import gr.scytalys.team3.Technikon.dto.RepairResponseDTO;
import gr.scytalys.team3.Technikon.dto.RepairUpdateDTO;
import gr.scytalys.team3.Technikon.security.service.UserInfoDetails;
import gr.scytalys.team3.Technikon.service.PropertyService;
import gr.scytalys.team3.Technikon.service.RepairService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/owner/")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class RepairController {
    private final RepairService repairService;
    private final PropertyService propertyService;

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
    @PostMapping("property/{propertyId}/repair")
    ResponseEntity<RepairResponseDTO> createRepair(@PathVariable long propertyId,
                                                   @RequestBody RepairCreateDTO repairCreateDTO, Authentication authentication){
        if (authentication.getAuthorities().stream().noneMatch(sga -> sga.getAuthority().equals("ROLE_ADMIN"))) {
            UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
            if(propertyId == 0 || propertyService
                    .findAllPropertiesByOwnerId(userInfoDetails.getId())
                    .stream().noneMatch(prop -> prop.getId() == propertyId)) {
                throw new AccessDeniedException("Unauthorized to create repair for this property");
            }
            }
            HttpHeaders headers = new HttpHeaders();
            headers.add("message", "Repair Created");
        return new ResponseEntity<>(repairService.createRepair(propertyId, repairCreateDTO), headers, HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
    @GetMapping("{propertyId}/repair/{repairId}")
    ResponseEntity<RepairResponseDTO> getRepairById(@PathVariable long propertyId, @PathVariable long repairId,
                                                    Authentication authentication){

        if (authentication.getAuthorities().stream().noneMatch(sga -> sga.getAuthority().equals("ROLE_ADMIN"))) {
            UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
            if(propertyId == 0 || propertyService
                    .findAllPropertiesByOwnerId(userInfoDetails.getId())
                    .stream().noneMatch(prop -> prop.getId() == propertyId)) {
                throw new AccessDeniedException("Unauthorized to get this repair");
            }
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Repair Retrieved");
        RepairResponseDTO repair = repairService.getRepairById(repairId);
        return new ResponseEntity<>(repair, headers, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
    @GetMapping("{propertyId}/repair")
    ResponseEntity<List<RepairResponseDTO>> getRepairsByPropertyId(@PathVariable long propertyId, Authentication authentication){
        if (authentication.getAuthorities().stream().noneMatch(sga -> sga.getAuthority().equals("ROLE_ADMIN"))) {
            UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
            if(propertyId == 0 || propertyService
                    .findAllPropertiesByOwnerId(userInfoDetails.getId())
                    .stream().noneMatch(prop -> prop.getId() == propertyId)) {
                throw new AccessDeniedException("Unauthorized to create repair for this property");
            }
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Retrieving List of repairs ");
        List<RepairResponseDTO> repairs = repairService.getRepairsByPropertyId(propertyService.getPropertyById(propertyId).getId());
        return new ResponseEntity<>(repairs, headers, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
    @GetMapping("/property/{propertryId}/repair/{date}")
    ResponseEntity<List<RepairResponseDTO>> getRepairsByDate(@PathVariable @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate date, @PathVariable long propertyId, Authentication authentication){
        if (authentication.getAuthorities().stream().noneMatch(sga -> sga.getAuthority().equals("ROLE_ADMIN"))) {
            UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
            if(propertyId == 0 || propertyService
                    .findAllPropertiesByOwnerId(userInfoDetails.getId())
                    .stream().noneMatch(prop -> prop.getId() == propertyId)) {
                throw new AccessDeniedException("Unauthorized to create repair for this property");
            }
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Retrieving a List of repairs made on a certain date.");
        List<RepairResponseDTO> repairs = repairService.getRepairsByRepairDate(date);
        return new ResponseEntity<>(repairs, headers, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
    @GetMapping("/repair/{startDate}/{endDate}")
    ResponseEntity<List<RepairResponseDTO>> getRepairsByRangeOfDates(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate, Authentication authentication){
        if (authentication.getAuthorities().stream().noneMatch(sga -> sga.getAuthority().equals("ROLE_ADMIN"))) {
            throw new AccessDeniedException("Unauthorized to create repair for this property");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Retrieving a List of repairs made between a certain date.");
        List<RepairResponseDTO> repairs = repairService.getRepairByRangeOfDates(startDate, endDate);
        return new ResponseEntity<>(repairs, headers, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
    @GetMapping("{ownerId}/property/repair")
    ResponseEntity<List<RepairResponseDTO>> getRepairsByPropertyOwnerId(@PathVariable long ownerId, Authentication authentication){
        if (authentication.getAuthorities().stream().noneMatch(sga -> sga.getAuthority().equals("ROLE_ADMIN"))) {
            UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
            if(ownerId != userInfoDetails.getId()) {
                throw new AccessDeniedException("Unauthorized to create repair for this property");
            }
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Retrieving a List of repairs for a certain propertyOwner");
        List<RepairResponseDTO> repairs = repairService.getRepairsByPropertyOwnerId(ownerId);
        return new ResponseEntity<>(repairs, headers, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
    @GetMapping("{propertyId}/repair/")
    ResponseEntity<BigDecimal> getTotalCostOfRepairsForPropertyId(@PathVariable long propertyId, Authentication authentication){
        if (authentication.getAuthorities().stream().noneMatch(sga -> sga.getAuthority().equals("ROLE_ADMIN"))) {
            throw new AccessDeniedException("Only for Admins");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Calculation of total cost");
        BigDecimal totalCost = repairService.getTotalCost(propertyId);
        return new ResponseEntity<>(totalCost, headers, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
    @DeleteMapping("/property/{propertyId}/repair/{repairId}")
    ResponseEntity<Boolean> deleteRepairWithRepairId(@PathVariable long repairId, @PathVariable long propertyId, Authentication authentication){
        if (authentication.getAuthorities().stream().noneMatch(sga -> sga.getAuthority().equals("ROLE_ADMIN"))) {
            UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
            if(propertyId == 0 || propertyService
                    .findAllPropertiesByOwnerId(userInfoDetails.getId())
                    .stream().noneMatch(prop -> prop.getId() == propertyId)) {
                throw new AccessDeniedException("Unauthorized to delete repair for this property");
            }
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Deleting repair with id: " + repairId);
        Boolean isDeleted = repairService.deleteRepair(repairId);
        return new ResponseEntity<>(isDeleted, headers, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
    @PutMapping("property/{propertyId}/repair/{repairId}")
    ResponseEntity<RepairResponseDTO> updateRepair(@PathVariable long repairId,@RequestBody RepairUpdateDTO repairUpdateDTO, @PathVariable long propertyId, Authentication authentication){
        if (authentication.getAuthorities().stream().noneMatch(sga -> sga.getAuthority().equals("ROLE_ADMIN"))) {
            UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
            if(propertyId == 0 || propertyService
                    .findAllPropertiesByOwnerId(userInfoDetails.getId())
                    .stream().noneMatch(prop -> prop.getId() == propertyId)) {
                throw new AccessDeniedException("Unauthorized to update repair for this property");
            }
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Updating the repair" );
        RepairResponseDTO updatedRepair = repairService.updateRepair(repairId, repairUpdateDTO);
        return  new ResponseEntity<>(updatedRepair, headers, HttpStatus.OK);
    }
}

