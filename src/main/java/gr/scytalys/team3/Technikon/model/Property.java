package gr.scytalys.team3.Technikon.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long propertyId;
    @Column(unique = true)
    private long propertyIN;
    private String address;
    private long yearOfConstruct;
    @ManyToOne
    @NotNull
    private PropertyOwner propertyOwner;
    private String picturePath;
//    @Embedded
//    private PropertyCoordinates propertyCoordinates;
    private TypeOfProperty typeOfProperty;
    @OneToMany(mappedBy = "property")
    private List<Repair> repairTasks;

    private boolean isActive = true;

}
