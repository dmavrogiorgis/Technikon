package gr.scytalys.team3.Technikon.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Repair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate dateOfRepair;
    private String description;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private StatusOfRepair statusOfRepair = StatusOfRepair.PENDING;
    private TypeOfRepair typeOfRepair;
    private BigDecimal costOfRepair;

}
