package gr.scytalys.team3.Technikon.controller;

import gr.scytalys.team3.Technikon.dto.RepairCreateDTO;
import gr.scytalys.team3.Technikon.dto.RepairResponseDTO;
import gr.scytalys.team3.Technikon.service.RepairService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/repair")
@AllArgsConstructor
public class RepairController {
    private final RepairService repairService;

    @PostMapping("")
    ResponseEntity<RepairResponseDTO> createRepair(@RequestBody RepairCreateDTO repairCreateDTO){
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Repair Created");
        return new ResponseEntity<>(repairService.createRepair(repairCreateDTO), headers, HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<RepairResponseDTO> getRepairById(@RequestParam long repairId){
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Repair Retrieved");
        RepairResponseDTO repair = repairService.getRepairById(repairId);
        return new ResponseEntity<>(repair, headers, HttpStatus.OK);
    }

    @GetMapping("/repairs")
    ResponseEntity<List<RepairResponseDTO>> getRepairsByPropertyId(@RequestParam long propertyId){
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Retrieving List of repairs ");
        List<RepairResponseDTO> repairs = repairService.getRepairsByPropertyId(propertyId);
        return new ResponseEntity<>(repairs, headers, HttpStatus.OK);
    }

    @GetMapping("/repairsDateRange")
    ResponseEntity<List<RepairResponseDTO>> getRepairsByRangeOfDates(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate){
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Retrieving a List of repairs made between a certain date.");
        List<RepairResponseDTO> repairs = repairService.getRepairByRangeOfDates(startDate, endDate);
        return new ResponseEntity<>(repairs, headers, HttpStatus.OK);
    }

    @GetMapping("/repairsDate")
    ResponseEntity<List<RepairResponseDTO>> getRepairsByDate(LocalDate date){
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Retrieving a List of repairs made on a certain date.");
        List<RepairResponseDTO> repairs = repairService.findRepairsByRepairDate(date);
        return new ResponseEntity<>(repairs, headers, HttpStatus.OK);
    }

    @GetMapping("/repairByPropertyOwnerId")
    ResponseEntity<List<RepairResponseDTO>> getRepairsByPropertyOwnerId(long propertyOwnerId){
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Retrieving a List of repairs for a certain propertyOwner");
        List<RepairResponseDTO> repairs = repairService.getRepairsByPropertyOwnerId(propertyOwnerId);
        return new ResponseEntity<>(repairs, headers, HttpStatus.OK);
    }

    @GetMapping("/totalCostOftRepairs")
    ResponseEntity<BigDecimal> getTotalCostOfRepairsForPropertyId(long propertyId){
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Calculation of total cost");
        BigDecimal totalCost = repairService.getTotalCost(propertyId);
        return new ResponseEntity<>(totalCost, headers, HttpStatus.OK);
    }
    @DeleteMapping("/deleteRepair")
    ResponseEntity<Boolean> deleteRepairWithRepairId(long repairId){
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Deleting repair with id: " + repairId);
        Boolean isDeleted = repairService.deleteRepair(repairId);
        return new ResponseEntity<>(isDeleted, headers, HttpStatus.OK);
    }

    @PutMapping("/updateRepair")
    ResponseEntity<RepairResponseDTO> updateRepair(long repairId, RepairResponseDTO repairResponseDTO){
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Updating the repair" );
        RepairResponseDTO updatedRepair = repairService.updateRepair(repairId, repairResponseDTO);
        return  new ResponseEntity<>(updatedRepair, headers, HttpStatus.OK);
    }
}
