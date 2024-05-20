package gr.scytalys.team3.Technikon.dto;

import gr.scytalys.team3.Technikon.model.PropertyCoordinates;
import gr.scytalys.team3.Technikon.model.PropertyOwner;
import gr.scytalys.team3.Technikon.model.Repair;
import gr.scytalys.team3.Technikon.model.TypeOfProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyDTO {

    private long id;
    private long propertyIN;
    private String address;
    private long yearOfConstruct;
    private long propertyOwnerId;
    private String picturePath;
    //private PropertyCoordinates propertyCoordinates;
    private TypeOfProperty typeOfProperty;
}