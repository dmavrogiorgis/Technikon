package gr.scytalys.team3.Technikon.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long propertyId;
    @Column(unique = true)
    private String propertyIN;
    private String address;
    private String yearOfConstruct;
    @ManyToOne
    @NotNull
    private PropertyOwner propertyOwner;
    private String picturePath;
    private String latitude;
    private String longitude;
    private TypeOfProperty typeOfProperty;
}
