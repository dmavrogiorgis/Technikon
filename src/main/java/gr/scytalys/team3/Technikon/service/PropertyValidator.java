package gr.scytalys.team3.Technikon.service;

import gr.scytalys.team3.Technikon.dto.ResponseApi;
import gr.scytalys.team3.Technikon.model.Property;
import org.apache.coyote.Response;

public interface PropertyValidator {
    void validate(Property property);
}
