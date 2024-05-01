package gr.scytalys.team3.Technikon.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate orderDate;
    @ManyToOne
    private Repair repair;
    @ManyToOne
    private Property property;
}
