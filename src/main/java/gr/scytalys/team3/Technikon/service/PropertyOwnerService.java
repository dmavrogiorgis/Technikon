package gr.scytalys.team3.Technikon.service;

import gr.scytalys.team3.Technikon.dto.PropertyOwnerDTO;
import gr.scytalys.team3.Technikon.dto.PropertyOwnerSearchDTO;
import gr.scytalys.team3.Technikon.model.PropertyOwnerUpdateDTO;

public interface PropertyOwnerService {
    //CREATE PROPERTY OWNER
    PropertyOwnerDTO createPropertyOwner(PropertyOwnerDTO propertyOwnerDTO);

    //SEARCH FOR PROPERTY OWNER
    PropertyOwnerDTO searchPropertyOwner(PropertyOwnerSearchDTO propertyOwnerSearchDTO);

    //UPDATE PROPERTY OWNER
    void updatePropertyOwner(String propertyOwnerTIN, PropertyOwnerUpdateDTO propertyOwnerUpdateDTO);

    //DELETE PROPERTY OWNER
    void deletePropertyOwner(String propertyOwnerTIN);
}
