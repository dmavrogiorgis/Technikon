package gr.scytalys.team3.Technikon.model;

import jakarta.persistence.Embeddable;
import lombok.Data;


import java.math.BigDecimal;

@Data
@Embeddable
public class PropertyCoordinates {
    private BigDecimal latitude;
    private BigDecimal longitude;
}
