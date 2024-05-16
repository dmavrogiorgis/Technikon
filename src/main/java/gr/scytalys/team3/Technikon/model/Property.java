package gr.scytalys.team3.Technikon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(unique = true, nullable = false)
    private String propertyIN;
    private String address;
    private String yearOfConstruct;
    private String picturePath;
    @ManyToOne
    @NotNull
    @JsonIgnore
    private PropertyOwner propertyOwner;
    @Embedded
    private PropertyCoordinates propertyCoordinates;
    private TypeOfProperty typeOfProperty;
    @JsonIgnore
    @OneToMany(mappedBy = "property")
    private List<Repair> repairs;

}
