package gr.scytalys.team3.Technikon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * The entity for the repairs that the property owner
 * and the admin will create
 * @author MichalisSpn
 */

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Repair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private TypeOfRepair typeOfRepair;
    private String description;
    private BigDecimal costOfRepair;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate repairDate;
    @Enumerated(EnumType.STRING)
    @NotNull
    private StatusOfRepair statusOfRepair = StatusOfRepair.PENDING;
    private boolean isActive = true;
    @JsonIgnore
    @ManyToOne
    @NotNull
    private Property property;

}
