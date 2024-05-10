package gr.scytalys.team3.Technikon.service;

import gr.scytalys.team3.Technikon.dto.PropertyOwnerDTO;
import gr.scytalys.team3.Technikon.mapper.PropertyOwnerMapper;
import gr.scytalys.team3.Technikon.repository.PropertyOwnerRepository;
import lombok.AllArgsConstructor;
import org.mapstruct.ap.shaded.freemarker.template.utility.NullArgumentException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PropertyOwnerServiceImpl implements PropertyOwnerService{

    private final PropertyOwnerRepository propertyOwnerRepository;
    private final PropertyOwnerMapper propertyOwnerMapper;
    private final PropertyOwnerValidator propertyOwnerValidator;

    @Override
    public PropertyOwnerDTO createPropertyOwner(PropertyOwnerDTO propertyOwnerDTO) {

        if (propertyOwnerDTO == null){
            throw new NullArgumentException("Property Owner is null!");
        }

        propertyOwnerValidator.validatePropertyOwner(propertyOwnerDTO.getTin(),
                                                     propertyOwnerDTO.getEmail(),
                                                     propertyOwnerDTO.getPhoneNumber());

        try {
            return propertyOwnerMapper
                    .toPropertyOwnerDto(propertyOwnerRepository
                                            .save(propertyOwnerMapper
                                                    .toPropertyOwner(propertyOwnerDTO)));
        } catch (Exception e) {
            throw new DataIntegrityViolationException(e.getMessage());
        }
    }

    @Override
    public PropertyOwnerDTO getPropertyOwnerById(long propertyOwnerId) {
        return null;
    }

    @Override
    public PropertyOwnerDTO getPropertyOwnerByEmail(String email) {
        return null;
    }

    @Override
    public PropertyOwnerDTO getPropertyOwnerByUsername(String username) {
        return null;
    }

    @Override
    public boolean updatePropertyOwner(PropertyOwnerDTO propertyOwnerDTO) {
        return false;
    }

    @Override
    public boolean deletePropertyOwner(long id) {
        return false;
    }
}
