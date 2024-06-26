package gr.scytalys.team3.Technikon.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import gr.scytalys.team3.Technikon.model.StatusOfRepair;
import gr.scytalys.team3.Technikon.model.TypeOfRepair;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepairResponseDTO {
    private long id;
    private TypeOfRepair typeOfRepair;
    private StatusOfRepair statusOfRepair;
    private String description;
    private BigDecimal costOfRepair;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate repairDate;
    private long propertyId;
    private boolean isActive;
}
