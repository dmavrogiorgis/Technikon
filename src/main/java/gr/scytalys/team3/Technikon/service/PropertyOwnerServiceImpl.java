package gr.scytalys.team3.Technikon.service;

import gr.scytalys.team3.Technikon.repository.PropertyOwnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PropertyOwnerServiceImpl {
    private final PropertyOwnerRepository propertyOwnerRepository;
}
