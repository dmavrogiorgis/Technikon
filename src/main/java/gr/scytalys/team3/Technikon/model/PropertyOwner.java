package gr.scytalys.team3.Technikon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PropertyOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Column(unique = true)
    private long tin;
    private String name;
    private String lastname;
    private String address;
    private String phoneNumber;
    @Email
    private String email;
    @Column(unique = true)
    private String username;
    private String password;
    @JsonIgnore
    @OneToMany (mappedBy = "propertyOwner")
    private List<Property> properties = new ArrayList<>();
    private boolean active = true;
}
