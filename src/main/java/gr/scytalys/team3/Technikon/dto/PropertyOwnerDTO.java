package gr.scytalys.team3.Technikon.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PropertyOwnerDTO {

    private long id;
    private String tin;
    private String name;
    private String surname;
    private String address;
    private String phoneNumber;
    private String email;
    private String username;
    private String password;
    private boolean isActive;
}




