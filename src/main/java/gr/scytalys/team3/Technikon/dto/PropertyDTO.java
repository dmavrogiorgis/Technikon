package gr.scytalys.team3.Technikon.dto;

import gr.scytalys.team3.Technikon.model.TypeOfProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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