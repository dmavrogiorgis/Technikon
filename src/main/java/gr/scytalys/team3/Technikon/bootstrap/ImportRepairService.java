package gr.scytalys.team3.Technikon.bootstrap;

import com.github.javafaker.Faker;
import gr.scytalys.team3.Technikon.model.*;
import gr.scytalys.team3.Technikon.model.*;
import gr.scytalys.team3.Technikon.repository.PropertyOwnerRepository;
import gr.scytalys.team3.Technikon.repository.PropertyRepository;
import gr.scytalys.team3.Technikon.repository.RepairRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;

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
        property.setPropertyOwner(propertyOwner);
        property.setAddress("assdfads");
        propertyRepository.save(property);


        Repair repair = new Repair();
        repair.setCostOfRepair(new BigDecimal(100));
        repair.setTypeOfRepair(TypeOfRepair.PAINTING);
        repair.setProperty(property);
        repair.setRepairDate(LocalDate.ofEpochDay(21/5/2024));
        repairRepository.save(repair);

        Repair repair1 = new Repair();
        repair1.setCostOfRepair(new BigDecimal(300));
        repair1.setTypeOfRepair(TypeOfRepair.INSULATION);
        repair1.setProperty(property);
        repairRepository.save(repair1);

        Repair repair2 = new Repair();
        repair2.setCostOfRepair(new BigDecimal(400));
        repair2.setTypeOfRepair(TypeOfRepair.ELECTRICAL_WORK);
        repair2.setProperty(property);
        repairRepository.save(repair2);

        Repair repair3 = new Repair();
        repair3.setCostOfRepair(new BigDecimal(150));
        repair3.setTypeOfRepair(TypeOfRepair.PLUMBING);
        repair3.setProperty(property);
        repairRepository.save(repair3);

        Repair repair4 = new Repair();
        repair4.setCostOfRepair(new BigDecimal(110));
        repair4.setTypeOfRepair(TypeOfRepair.FRAMES);
        repairRepository.save(repair4);
    }
}
