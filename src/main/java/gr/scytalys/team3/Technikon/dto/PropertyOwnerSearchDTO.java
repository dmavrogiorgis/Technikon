package gr.scytalys.team3.Technikon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyOwnerSearchDTO {
    private String tin;
    private String email;
    private String username;
}
