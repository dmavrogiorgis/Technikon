package gr.scytalys.team3.Technikon.bootstrap;

import gr.scytalys.team3.Technikon.model.Property;
import gr.scytalys.team3.Technikon.model.PropertyOwner;
import gr.scytalys.team3.Technikon.model.Repair;
import gr.scytalys.team3.Technikon.model.TypeOfRepair;
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
import java.util.List;

@AllArgsConstructor
@Configuration
@Slf4j
public class ImportRepairService {
private RepairRepository repairRepository;
private PropertyRepository propertyRepository;
private PropertyOwnerRepository propertyOwnerRepository;
    @Bean
    public CommandLineRunner myCommandLineRunner(){
        return this::run;
    }

    private void run(String... args) {
        PropertyOwner propertyOwner = new PropertyOwner();
        propertyOwner.setActive(true);
        propertyOwner.setName("mpampis");
        propertyOwner.setUsername("tenes");
        propertyOwner.setTin(12345678);
        propertyOwner.setEmail("mpampis@tenes.gr");
        propertyOwner.setPassword("tsili123");
        propertyOwnerRepository.save(propertyOwner);

        Property property = new Property();
        property.setAddress("aqqwer");
        property.setPropertyIN(234);
        property.setPropertyOwner(propertyOwner);
        propertyRepository.save(property);

        Property property1 = new Property();
        property1.setAddress("aqqwer");
        property1.setPropertyIN(2345);
        property1.setPropertyOwner(propertyOwner);
        propertyRepository.save(property1);

        Repair repair = new Repair();
        repair.setCostOfRepair(new BigDecimal(100));
        repair.setTypeOfRepair(TypeOfRepair.valueOf("PAINTING"));
        repair.setRepairDate(LocalDate.of(2024, 05, 14));
        repair.setProperty(property);
        repairRepository.save(repair);

        Repair repair1 = new Repair();
        repair1.setCostOfRepair(new BigDecimal(300));
        repair1.setTypeOfRepair(TypeOfRepair.valueOf("INSULATION"));
        repair1.setRepairDate(LocalDate.of(2024, 05, 15));
        repair1.setProperty(property);
        repairRepository.save(repair1);

        Repair repair2 = new Repair();
        repair2.setCostOfRepair(new BigDecimal(400));
        repair2.setTypeOfRepair(TypeOfRepair.valueOf("FRAMES"));
        repair2.setRepairDate(LocalDate.of(2024, 05, 17));
        repair.setProperty(property1);
        repairRepository.save(repair2);

        Repair repair3 = new Repair();
        repair3.setCostOfRepair(new BigDecimal(150));
        repair3.setTypeOfRepair(TypeOfRepair.valueOf("PLUMBING"));
        repair3.setRepairDate(LocalDate.of(2024, 05, 19));
        repairRepository.save(repair3);

        Repair repair4 = new Repair();
        repair4.setCostOfRepair(new BigDecimal(110));
        repair4.setTypeOfRepair(TypeOfRepair.valueOf("ELECTRICAL_WORK"));
        repair4.setRepairDate(LocalDate.of(2024, 05, 20));
        repairRepository.save(repair4);

        Repair repair5 = new Repair();
        repair5.setCostOfRepair(new BigDecimal(110));
        repair5.setTypeOfRepair(TypeOfRepair.valueOf("ELECTRICAL_WORK"));
        repair5.setRepairDate(LocalDate.of(2024, 05, 16));
        repairRepository.save(repair5);


    }
}
