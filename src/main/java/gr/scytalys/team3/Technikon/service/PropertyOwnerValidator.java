package gr.scytalys.team3.Technikon.service;

import gr.scytalys.team3.Technikon.dto.PropertyOwnerDTO;

public interface PropertyOwnerValidator {
    void checkForNullCreation(PropertyOwnerDTO propertyOwnerDTO);
    void validatePropertyOwnerCreation(PropertyOwnerDTO propertyOwnerDTO);
    void validatePropertyOwnerUpdate(String email);
}
