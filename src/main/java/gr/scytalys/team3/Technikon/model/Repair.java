package gr.scytalys.team3.Technikon.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Repair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String typeOfRepair;
    private String description;
    private BigDecimal costOfRepair;
}
