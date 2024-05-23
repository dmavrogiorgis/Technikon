package gr.scytalys.team3.Technikon.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import gr.scytalys.team3.Technikon.dto.RepairCreateDTO;
import gr.scytalys.team3.Technikon.dto.RepairResponseDTO;
import gr.scytalys.team3.Technikon.dto.RepairUpdateDTO;
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
@RequestMapping("/api/owner/")
@AllArgsConstructor
public class RepairController {
    private final RepairService repairService;

    @PostMapping("property/{propertyId}/repair")
    ResponseEntity<RepairResponseDTO> createRepair(@PathVariable long propertyId,
                                                   @RequestBody RepairCreateDTO repairCreateDTO){
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Repair Created");
        return new ResponseEntity<>(repairService.createRepair(propertyId, repairCreateDTO), headers, HttpStatus.CREATED);
    }

    @GetMapping("{propertyId}/repair/{repairId}")
    ResponseEntity<RepairResponseDTO> getRepairById(@PathVariable long repairId){
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Repair Retrieved");
        RepairResponseDTO repair = repairService.getRepairById(repairId);
        return new ResponseEntity<>(repair, headers, HttpStatus.OK);
    }

    @GetMapping("{propertyId}/repair")
    ResponseEntity<List<RepairResponseDTO>> getRepairsByPropertyId(@PathVariable long propertyId){
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Retrieving List of repairs ");
        List<RepairResponseDTO> repairs = repairService.getRepairsByPropertyId(propertyId);
        return new ResponseEntity<>(repairs, headers, HttpStatus.OK);
    }

    @GetMapping("/repair/{date}")
    ResponseEntity<List<RepairResponseDTO>> getRepairsByDate(@PathVariable @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate date){
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Retrieving a List of repairs made on a certain date.");
        List<RepairResponseDTO> repairs = repairService.getRepairsByRepairDate(date);
        return new ResponseEntity<>(repairs, headers, HttpStatus.OK);
    }

    @GetMapping("/repair/{startDate}/{endDate}")
    ResponseEntity<List<RepairResponseDTO>> getRepairsByRangeOfDates(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate){
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Retrieving a List of repairs made between a certain date.");
        List<RepairResponseDTO> repairs = repairService.getRepairByRangeOfDates(startDate, endDate);
        return new ResponseEntity<>(repairs, headers, HttpStatus.OK);
    }

    @GetMapping("{ownerId}/property/repair")
    ResponseEntity<List<RepairResponseDTO>> getRepairsByPropertyOwnerId(@PathVariable long ownerId){
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Retrieving a List of repairs for a certain propertyOwner");
        List<RepairResponseDTO> repairs = repairService.getRepairsByPropertyOwnerId(ownerId);
        return new ResponseEntity<>(repairs, headers, HttpStatus.OK);
    }

    @GetMapping("{propertyId}/repair/")
    ResponseEntity<BigDecimal> getTotalCostOfRepairsForPropertyId(@PathVariable long propertyId){
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Calculation of total cost");
        BigDecimal totalCost = repairService.getTotalCost(propertyId);
        return new ResponseEntity<>(totalCost, headers, HttpStatus.OK);
    }
    @DeleteMapping("/repair/{repairId}")
    ResponseEntity<Boolean> deleteRepairWithRepairId(@PathVariable long repairId){
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Deleting repair with id: " + repairId);
        Boolean isDeleted = repairService.deleteRepair(repairId);
        return new ResponseEntity<>(isDeleted, headers, HttpStatus.OK);
    }

    @PutMapping("/repair/{repairId}")
    ResponseEntity<RepairResponseDTO> updateRepair(@PathVariable long repairId,@RequestBody RepairUpdateDTO repairUpdateDTO){
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Updating the repair" );
        RepairResponseDTO updatedRepair = repairService.updateRepair(repairId, repairUpdateDTO);
        return  new ResponseEntity<>(updatedRepair, headers, HttpStatus.OK);
    }
}
