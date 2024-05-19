package gr.scytalys.team3.Technikon.service;

import gr.scytalys.team3.Technikon.dto.PropertyOwnerResponseDTO;
import gr.scytalys.team3.Technikon.dto.PropertyOwnerSearchDTO;
import gr.scytalys.team3.Technikon.dto.PropertyOwnerUpdateDTO;

public interface PropertyOwnerService {

    //SEARCH FOR PROPERTY OWNER
    PropertyOwnerResponseDTO searchPropertyOwner(PropertyOwnerSearchDTO propertyOwnerSearchDTO);

    //UPDATE PROPERTY OWNER
    void updatePropertyOwner(String propertyOwnerTIN, PropertyOwnerUpdateDTO propertyOwnerUpdateDTO);

    //DELETE PROPERTY OWNER
    void deletePropertyOwner(String propertyOwnerTIN);
}
