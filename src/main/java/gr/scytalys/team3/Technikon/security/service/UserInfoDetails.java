package gr.scytalys.team3.Technikon.security.service;

import gr.scytalys.team3.Technikon.model.Admin;
import gr.scytalys.team3.Technikon.model.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserInfoDetails implements UserDetails {
    private final String username;
    private final String tin;
    private final String email;
    private final long id;
    private final String password;
    private final List<GrantedAuthority> authorities;
    private final boolean accountNonExpired = true;
    private final boolean accountNonLocked = true;
    private final boolean credentialsNonExpired = true;
    private final boolean enabled = true;

    public UserInfoDetails(User user) {
        this.username = user.getUsername();
        this.tin = user.getTin();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.id = user.getId();

        if (user instanceof Admin){
            authorities = Arrays.stream(new String[]{"ROLE_ADMIN"})
                                .map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toList());
        }else {
            authorities = Arrays.stream(new String[]{"ROLE_USER"})
                                .map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toList());
        }
    }
}
