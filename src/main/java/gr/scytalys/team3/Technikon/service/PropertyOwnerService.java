package gr.scytalys.team3.Technikon.service;

import gr.scytalys.team3.Technikon.dto.PropertyDTO;
import gr.scytalys.team3.Technikon.dto.PropertyOwnerDTO;
import gr.scytalys.team3.Technikon.model.PropertyOwner;

public interface PropertyOwnerService {
    //CREATE PROPERTY OWNER
    PropertyOwnerDTO createPropertyOwner(PropertyOwnerDTO propertyOwnerDTO);

    //SEARCH FOR PROPERTY OWNER
    PropertyOwnerDTO getPropertyOwnerById(long propertyOwnerId);
    PropertyOwnerDTO getPropertyOwnerByEmail(String email);
    PropertyOwnerDTO getPropertyOwnerByUsername(String username);

    //UPDATE PROPERTY OWNER
    boolean updatePropertyOwner(PropertyOwnerDTO propertyOwnerDTO);

    //DELETE PROPERTY OWNER
    boolean deletePropertyOwner(long id);
}
