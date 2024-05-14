package gr.scytalys.team3.Technikon.service;

import gr.scytalys.team3.Technikon.dto.PropertyOwnerDTO;
import gr.scytalys.team3.Technikon.dto.PropertyOwnerSearchDTO;
import gr.scytalys.team3.Technikon.mapper.PropertyOwnerMapper;
import gr.scytalys.team3.Technikon.model.PropertyOwner;
import gr.scytalys.team3.Technikon.model.PropertyOwnerUpdateDTO;
import gr.scytalys.team3.Technikon.repository.PropertyOwnerRepository;
import gr.scytalys.team3.Technikon.repository.PropertyOwnerSpecifications;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.mapstruct.ap.shaded.freemarker.template.utility.NullArgumentException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.security.InvalidParameterException;
import java.util.List;


@Service
@AllArgsConstructor
public class PropertyOwnerServiceImpl implements PropertyOwnerService {

    private final PropertyOwnerRepository propertyOwnerRepository;
    private final PropertyOwnerValidator propertyOwnerValidator;
    private final PropertyOwnerMapper propertyOwnerMapper;

    @Override
    @Transactional
    @CacheEvict(value = "myPropertyOwners", allEntries = true)
    public PropertyOwnerDTO createPropertyOwner(PropertyOwnerDTO propertyOwnerDTO) {

        if (propertyOwnerDTO == null) {
            throw new NullArgumentException("Property Owner is null!");
        }

        propertyOwnerValidator.validatePropertyOwner(propertyOwnerDTO.getTin(),
                                                     propertyOwnerDTO.getEmail(),
                                                     propertyOwnerDTO.getPhoneNumber());

        return propertyOwnerMapper
                    .toPropertyOwnerDto(propertyOwnerRepository
                                            .save(propertyOwnerMapper
                                                    .toPropertyOwner(propertyOwnerDTO)));
    }

    @Override
    @Cacheable("myPropertyOwners")
    public PropertyOwnerDTO searchPropertyOwner(PropertyOwnerSearchDTO propertyOwnerSearchDTO) {

        if (propertyOwnerSearchDTO == null) {
            throw new NullArgumentException("Property Owner to search is null!");
        }

        String tin = propertyOwnerSearchDTO.getTin();
        String email = propertyOwnerSearchDTO.getEmail();
        String username = propertyOwnerSearchDTO.getUsername();

        if (tin == null && email == null && username == null) {
            throw new InvalidParameterException("TIN, email and username should not be null!");
        }
        Specification<PropertyOwner> spec = Specification.where(null);

        if (tin != null) {
            spec = spec.and(PropertyOwnerSpecifications.tinEquals(tin));
        }
        if (email != null) {
            spec = spec.and(PropertyOwnerSpecifications.emailEquals(email));
        }
        if (username != null) {
            spec = spec.and(PropertyOwnerSpecifications.usernameEquals(username));
        }

        return propertyOwnerMapper
                    .toPropertyOwnerDto(propertyOwnerRepository
                                            .findOne(spec)
                                            .orElseThrow(() -> new EntityNotFoundException("Property owner not found!")));
    }

    @Override
    @Transactional
    @CacheEvict(value = "myPropertyOwners", allEntries = true)
    public void updatePropertyOwner(String propertyOwnerTIN, PropertyOwnerUpdateDTO propertyOwnerUpdateDTO) {

        if (propertyOwnerTIN == null) {
            throw new InvalidParameterException("TIN should not be null!");
        }

        PropertyOwner propertyOwnerDB = getPropertyOwnerByTINSpec(propertyOwnerTIN);

        if (propertyOwnerUpdateDTO == null) {
            throw new NullArgumentException("Property Owner to update is null!");
        }

        String email = propertyOwnerUpdateDTO.getEmail();
        String password = propertyOwnerUpdateDTO.getPassword();
        String address = propertyOwnerUpdateDTO.getAddress();

        if (email == null && address == null && password == null) {
            throw new InvalidParameterException("Email, address and password are null!");
        }

        if (email != null) {
            propertyOwnerDB.setEmail(email);
        }
        if (address != null) {
            propertyOwnerDB.setAddress(address);
        }
        if (password != null) {
            propertyOwnerDB.setPassword(password);
        }
        propertyOwnerRepository.save(propertyOwnerDB);
    }

    @Override
    @Transactional
    @CacheEvict(value = "myPropertyOwners", allEntries = true)
    public void deletePropertyOwner(String propertyOwnerTIN) {
        if (propertyOwnerTIN == null) {
            throw new InvalidParameterException("TIN should not be null!");
        }

        PropertyOwner propertyOwnerDB = getPropertyOwnerByTINSpec(propertyOwnerTIN);

        List<String> properties = propertyOwnerRepository.findPropertiesByPropertyOwnerTIN(propertyOwnerTIN);
        if (properties.isEmpty()){
            propertyOwnerRepository.deleteById(propertyOwnerDB.getId());
        }else {
            propertyOwnerDB.setActive(false);
            propertyOwnerRepository.save(propertyOwnerDB);
        }
    }

    private PropertyOwner getPropertyOwnerByTINSpec(String propertyOwnerTIN){
        Specification<PropertyOwner> spec = Specification.where(PropertyOwnerSpecifications.isActive()); //To ensure that inactive users cannot change their data
        spec = spec.and(PropertyOwnerSpecifications.tinEquals(propertyOwnerTIN));

        //TO MAKE A SEPARATE METHOD searchByTIN
        return propertyOwnerRepository
                    .findOne(spec)
                    .orElseThrow(() -> new EntityNotFoundException("Property owner not found!"));
    }
}
