package gr.scytalys.team3.Technikon.security.service;

import gr.scytalys.team3.Technikon.dto.PropertyOwnerDTO;
import gr.scytalys.team3.Technikon.dto.PropertyOwnerResponseDTO;
import gr.scytalys.team3.Technikon.mapper.PropertyOwnerMapper;
import gr.scytalys.team3.Technikon.model.Admin;
import gr.scytalys.team3.Technikon.model.PropertyOwner;
import gr.scytalys.team3.Technikon.repository.AdminRepository;
import gr.scytalys.team3.Technikon.repository.PropertyOwnerRepository;
import gr.scytalys.team3.Technikon.service.PropertyOwnerValidator;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserInfoService implements UserDetailsService {
    private final PropertyOwnerRepository propertyOwnerRepository;
    private final AdminRepository adminRepository;
    private final PropertyOwnerMapper propertyOwnerMapper;
    private final PropertyOwnerValidator propertyOwnerValidator;
    private final PasswordEncoder encoder;


    @Override
    public UserInfoDetails loadUserByUsername(String username) {
        Optional<PropertyOwner> poUserDetail = propertyOwnerRepository.findByUsername(username);
        if (poUserDetail.isPresent()) {
            return poUserDetail.map(UserInfoDetails::new).get();
        }
        Optional<Admin> adminUserDetail = adminRepository.findByUsername(username);
        if (adminUserDetail.isPresent()) {
            return adminUserDetail.map(UserInfoDetails::new).get();
        }
        throw new BadCredentialsException("Username not found " + username);
    }

    @Transactional
    @CacheEvict(value = "PropertyOwners", allEntries = true)
    public PropertyOwnerResponseDTO createPropertyOwner(PropertyOwnerDTO propertyOwnerDTO) {

        propertyOwnerValidator.checkForNullCreation(propertyOwnerDTO);

        propertyOwnerValidator.validatePropertyOwnerCreation(propertyOwnerDTO);

        PropertyOwner propertyOwnerDB = propertyOwnerMapper.toPropertyOwner(propertyOwnerDTO);
        propertyOwnerDB.setPassword(encoder.encode(propertyOwnerDB.getPassword()));

        try{
            propertyOwnerRepository.save(propertyOwnerDB);
            return propertyOwnerMapper.toPropertyOwnerResponseDto(propertyOwnerDB);
        }catch (Exception ex){
            throw new DataIntegrityViolationException(ex.getMessage());
        }

    }

}
