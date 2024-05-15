package gr.scytalys.team3.Technikon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyOwnerUpdateDTO {
    private String address;
    private String email;
    private String password;
}
