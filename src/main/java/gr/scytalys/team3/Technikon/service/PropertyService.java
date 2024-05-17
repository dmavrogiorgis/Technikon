package gr.scytalys.team3.Technikon.service;

import gr.scytalys.team3.Technikon.dto.PropertyDTO;
import gr.scytalys.team3.Technikon.model.Property;

import java.util.List;


public interface PropertyService {
    PropertyDTO createProperty(long ownerId, PropertyDTO propertyDTO);
    PropertyDTO getPropertyById(long propertyId);
    List<PropertyDTO> getAllProperties();
    List<PropertyDTO> getAllActiveProperties();
    List<PropertyDTO> findAllPropertiesByOwnerId(long id);
    PropertyDTO updateProperty( PropertyDTO propertyDTO);
    void deletePropertyById(long propertyId);
    boolean hasRepairTasks(long propertyId);
    void deactivatePropertyById(long propertyId);
    String deleteOrDeactivatePropertyById(long propertyId);

}
