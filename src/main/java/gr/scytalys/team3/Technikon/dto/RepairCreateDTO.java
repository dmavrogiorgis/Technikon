package gr.scytalys.team3.Technikon.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import gr.scytalys.team3.Technikon.model.StatusOfRepair;
import gr.scytalys.team3.Technikon.model.TypeOfRepair;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RepairCreateDTO {
    private TypeOfRepair typeOfRepair;
    private String description;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate repairDate;
    private long propertyId;
}
