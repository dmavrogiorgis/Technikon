package gr.scytalys.team3.Technikon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {
    private long id;
    private String username;
    private String tin;
    private String email;
    private String token;
    private List<GrantedAuthority> authorities;
}
