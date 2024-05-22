package gr.scytalys.team3.Technikon.service.impl;

import gr.scytalys.team3.Technikon.dto.PropertyDTO;
import gr.scytalys.team3.Technikon.mapper.PropertyMapper;
import gr.scytalys.team3.Technikon.model.Property;
import gr.scytalys.team3.Technikon.repository.PropertyRepository;
import gr.scytalys.team3.Technikon.service.PropertyService;
import gr.scytalys.team3.Technikon.service.PropertyValidator;
import jakarta.el.PropertyNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.mapstruct.ap.shaded.freemarker.template.utility.NullArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor_ = { @Autowired})
public class PropertyServiceImpl implements PropertyService {
    private final PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;
    private final PropertyValidator propertyValidator;

    @Override
    public PropertyDTO createProperty(long ownerId, PropertyDTO propertyDTO) {

        if(propertyDTO == null){
            throw new NullArgumentException("Property is null!");
        }
        if(propertyDTO.getPropertyOwnerId() == 0){
            throw new NullArgumentException("Property Owner is null!");
        }
        if(ownerId!=propertyDTO.getPropertyOwnerId()){
            throw new InvalidParameterException("Cannot create property");
        }
        if(propertyDTO.getAddress().matches("")){
            throw new InvalidParameterException("Cannot create property without address");
        }
        Property savedProperty = propertyMapper.toProperty(propertyDTO);
        propertyValidator.validate(savedProperty);
        propertyRepository.save(savedProperty);
        return propertyMapper.toPropertyDto(savedProperty);

    }

    @Override
    public PropertyDTO getPropertyById(long propertyId) {
        Property found = propertyRepository.findById(propertyId)
                .filter(Property::isActive)
                .orElse(null);
        return propertyMapper.toPropertyDto(found);
    }

    @Transactional
    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO) {
        Property found = propertyRepository.findById(propertyDTO.getId())
                .filter(Property::isActive)
                .orElseThrow();

            if (propertyDTO.getPropertyIN() != 0) {
                found.setPropertyIN(propertyDTO.getPropertyIN());
            }
           if (propertyDTO.getYearOfConstruct() != 0) {
                found.setYearOfConstruct(propertyDTO.getYearOfConstruct());
           }
           if(propertyDTO.getAddress()!= "") {
                found.setAddress(propertyDTO.getAddress());
           }
           propertyRepository.save(found);
        return propertyMapper.toPropertyDto(found);

    }

    @Override
    public void deletePropertyById(long propertyId) {
        propertyRepository.findById(propertyId)
                .filter(Property::isActive)
                .orElseThrow();
    }

    @Override
    public boolean hasRepairTasks(long propertyId) {
        Property found = propertyRepository.findById(propertyId)
                .filter(Property::isActive)
                .orElseThrow();
        return found.getRepairs()!=null;
    }

    @Override
    public void deactivatePropertyById(long propertyId) {
        Property found = propertyRepository.findById(propertyId).orElse(null);
        if (found != null) {
            found.setActive(false);
            propertyRepository.save(found);
        }
//                .filter(Property::isActive)
//                .orElseThrow();


    }

    @Override
    public String deleteOrDeactivatePropertyById(long propertyId) {
        Property found = propertyRepository.findById(propertyId).orElse(null);
//        if (hasRepairTasks(propertyId)) {
            deactivatePropertyById(propertyId);
            return "Deactivated";
//        } else {
//            deletePropertyById(propertyId);
//            return "Deleted";
//        }

    }
    //-----------ADMIN------------

    @Override
    public List<PropertyDTO> getAllProperties() {
        List<Property> properties = propertyRepository.findAll();
        return properties.stream()
                .map(propertyMapper::toPropertyDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<PropertyDTO> getAllActiveProperties() {
        List<Property> properties = propertyRepository.findAll();
        return properties.stream()
                .filter(Property::isActive)
                .map(propertyMapper::toPropertyDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PropertyDTO> findAllPropertiesByOwnerId(long id) {
        List<Property> properties = propertyRepository.findAllPropertiesByOwnerId(id);
        if (properties.isEmpty()){
            return null;
        }
        return properties.stream().filter(Property::isActive)
                .map(propertyMapper::toPropertyDto)
                .collect(Collectors.toList());
    }

}
