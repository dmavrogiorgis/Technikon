package gr.scytalys.team3.Technikon.controller;

import gr.scytalys.team3.Technikon.dto.RepairDTO;
import gr.scytalys.team3.Technikon.model.Repair;
import gr.scytalys.team3.Technikon.service.RepairService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
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
    ResponseEntity<RepairDTO> createRepair(@RequestBody RepairDTO repairDTO){
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Repair Created");
        return new ResponseEntity<>(repairService.createRepair(repairDTO), headers, HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<RepairDTO> getRepairById(@RequestParam long repaidId){
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Repair Retrieved");
        RepairDTO repair = repairService.getRepairById(repaidId);
        return new ResponseEntity<>(repair, headers, HttpStatus.OK);
    }

    @GetMapping("/repairs")
    ResponseEntity<List<RepairDTO>> getRepairsByPropertyId(@RequestParam long propertyId){
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Retrieving List of repairs ");
        List<RepairDTO> repairs = repairService.getRepairsByPropertyId(propertyId);
        return new ResponseEntity<>(repairs, headers, HttpStatus.OK);
    }

    @GetMapping("repairsDateRange")
    ResponseEntity<List<RepairDTO>> getRepairsByRangeOfDates(@DateTimeFormat(pattern="dd/MM/yyyy") LocalDate startDate, @RequestParam @DateTimeFormat(pattern="dd/MM/yyyy") LocalDate endDate){
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Retrieving a List of repairs made between a certain date.");
        List<RepairDTO> repairs = repairService.getRepairByRangeOfDates(startDate, endDate);
        return new ResponseEntity<>(repairs, headers, HttpStatus.OK);
    }

    @GetMapping("repairsDate")
    ResponseEntity<List<RepairDTO>> getRepairsByRangeOfDates(@DateTimeFormat(pattern="dd/MM/yyyy") LocalDate date){
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Retrieving a List of repairs made between a certain date.");
        List<RepairDTO> repairs = repairService.findRepairsByRepairDate(date);
        return new ResponseEntity<>(repairs, headers, HttpStatus.OK);
    }

    @GetMapping("repairByPropertyOwnerId")
    ResponseEntity<List<RepairDTO>> getRepairsByPropertyOwnerId(long propertyOwnerId){
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Retrieving a List of repairs for a certain propertyOwner");
        List<RepairDTO> repairs = repairService.getRepairsByPropertyOwnerId(propertyOwnerId);
        return new ResponseEntity<>(repairs, headers, HttpStatus.OK);
    }

    @GetMapping("totalCostOftRepairs")
    ResponseEntity<BigDecimal> getTotalCostOfRepairsForPropertyId(long propertyId){
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Calculation of total cost");
        BigDecimal totalCost = repairService.getTotalCost(propertyId);
        return new ResponseEntity<>(totalCost, headers, HttpStatus.OK);
    }
    @DeleteMapping("deleteRepair")
    ResponseEntity<Boolean> deleteRepairWithRepairId(long repairId){
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Deleting repair with id: " + repairId);
        Boolean isDeleted = repairService.deleteRepair(repairId);
        return new ResponseEntity<>(isDeleted, headers, HttpStatus.OK);
    }

    @PutMapping("updateRepair")
    ResponseEntity<RepairDTO> updateRepair(long repairId, RepairDTO repairDTO){
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Updating the repair" );
        RepairDTO updatedRepair = repairService.updateRepair(repairId, repairDTO);
        return  new ResponseEntity<>(updatedRepair, headers, HttpStatus.OK);
    }
}
