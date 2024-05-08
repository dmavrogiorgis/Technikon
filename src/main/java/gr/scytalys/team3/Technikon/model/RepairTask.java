package gr.scytalys.team3.Technikon.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class RepairTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate orderDate;
    @ManyToOne
    private Repair repair;
    @ManyToOne
    @JoinColumn(name = "propertyId", nullable = false)
    private Property property;
    private String workDescription;
}
