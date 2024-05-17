package gr.scytalys.team3.Technikon.bootstrap;

import gr.scytalys.team3.Technikon.model.Property;
import gr.scytalys.team3.Technikon.model.PropertyOwner;
import gr.scytalys.team3.Technikon.model.Repair;
import gr.scytalys.team3.Technikon.repository.PropertyOwnerRepository;
import gr.scytalys.team3.Technikon.repository.PropertyRepository;
import gr.scytalys.team3.Technikon.repository.RepairRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@AllArgsConstructor
@Configuration
@Slf4j
public class ImportRepairService {
private final RepairRepository repairRepository;
private final PropertyOwnerRepository propertyOwnerRepository;
private final PropertyRepository propertyRepository;

    @Bean
    public CommandLineRunner myCommandLineRunner(){
        return this::run;
    }

    private void run(String... args) {
        Repair repair = new Repair();
        repair.setCostOfRepair(new BigDecimal(100));
        repair.setTypeOfRepair("PAINTING");
        repairRepository.save(repair);

        Repair repair1 = new Repair();
        repair1.setCostOfRepair(new BigDecimal(300));
        repair1.setTypeOfRepair("INSULATION");
        repairRepository.save(repair1);

        Repair repair2 = new Repair();
        repair2.setCostOfRepair(new BigDecimal(400));
        repair2.setTypeOfRepair("FRAMES");
        repairRepository.save(repair2);

        Repair repair3 = new Repair();
        repair3.setCostOfRepair(new BigDecimal(150));
        repair3.setTypeOfRepair("PLUMBING");
        repairRepository.save(repair3);

        Repair repair4 = new Repair();
        repair4.setCostOfRepair(new BigDecimal(110));
        repair4.setTypeOfRepair("ELECTRICAL WORK");
        repairRepository.save(repair4);

        PropertyOwner propertyOwner = new PropertyOwner();
        propertyOwner.setTin("123456789");
        propertyOwner.setName("Dimitris");
        propertyOwner.setSurname("Mavrogiorgis");
        propertyOwner.setAddress("Something");
        propertyOwner.setPhoneNumber("6976500964");
        propertyOwner.setEmail("kati@gmail.com");
        propertyOwner.setUsername("dimmav");
        propertyOwner.setPassword("1234");
        propertyOwner.setActive(true);

        propertyOwnerRepository.save(propertyOwner);

        Property property = new Property();
        property.setPropertyIN(123456789);
        property.setPropertyOwner(propertyOwner);
        propertyRepository.save(property);
    }
}
