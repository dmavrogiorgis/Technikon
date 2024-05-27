package gr.scytalys.team3.Technikon.bootstrap;

import com.github.javafaker.Faker;
import gr.scytalys.team3.Technikon.model.Admin;
import gr.scytalys.team3.Technikon.model.*;
import gr.scytalys.team3.Technikon.model.Property;
import gr.scytalys.team3.Technikon.model.PropertyOwner;
import gr.scytalys.team3.Technikon.model.Repair;
import gr.scytalys.team3.Technikon.repository.AdminRepository;
import gr.scytalys.team3.Technikon.repository.PropertyOwnerRepository;
import gr.scytalys.team3.Technikon.repository.PropertyRepository;
import gr.scytalys.team3.Technikon.repository.RepairRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Configuration
@Slf4j
public class ImportRepairService {
private final RepairRepository repairRepository;
private final AdminRepository adminRepository;
private final PropertyOwnerRepository propertyOwnerRepository;
private final PropertyRepository propertyRepository;
private final PasswordEncoder encoder;

    private final static int numOfIterations = 20;

    @Bean
    public CommandLineRunner myCommandLineRunner(){
        return this::run;
    }

    private void run(String... args) {

        PropertyOwner propertyOwner = new PropertyOwner();
        propertyOwner.setTin("123456789");
        propertyOwner.setName("mpampis");
        propertyOwner.setSurname("tennes");
        propertyOwner.setAddress("Lamia");
        propertyOwner.setPhoneNumber("6946666666");
        propertyOwner.setEmail("mathe@mpalitsa.gr");
        propertyOwner.setUsername("tesseratesseradio");
        propertyOwner.setPassword("passW0rd");
        propertyOwnerRepository.save(propertyOwner);

        Property property = new Property();
        property.setPropertyIN(123);
        property.setPropertyOwner(propertyOwner);
        propertyRepository.save(property);

        Property property1 = new Property();
        property1.setPropertyIN(456);
        property1.setPropertyOwner(propertyOwner);
        propertyRepository.save(property1);
        System.out.println(property1);

        Repair repair = new Repair();
        repair.setCostOfRepair(new BigDecimal(100));
        repair.setTypeOfRepair(TypeOfRepair.PAINTING);
        repair.setProperty(property);
        repair.setRepairDate(LocalDate.now());
        repair.setStatusOfRepair(StatusOfRepair.IN_PROGRESS);
        repairRepository.save(repair);
        System.out.println(repair);

        Repair repair1 = new Repair();
        repair1.setCostOfRepair(new BigDecimal(300));
        repair1.setTypeOfRepair(TypeOfRepair.FRAMES);
        repair1.setProperty(property);
        repair1.setRepairDate(LocalDate.now());
        repair1.setStatusOfRepair(StatusOfRepair.IN_PROGRESS);
        repairRepository.save(repair1);

        Repair repair2 = new Repair();
        repair2.setCostOfRepair(new BigDecimal(400));
        repair2.setTypeOfRepair(TypeOfRepair.INSULATION);
        repair2.setProperty(property1);
        repair2.setRepairDate(LocalDate.now());
        repair2.setStatusOfRepair(StatusOfRepair.IN_PROGRESS);
        repairRepository.save(repair2);

        Repair repair3 = new Repair();
        repair3.setCostOfRepair(new BigDecimal(150));
        repair3.setTypeOfRepair(TypeOfRepair.PLUMBING);
        repair3.setProperty(property1);
        repair3.setRepairDate(LocalDate.now());
        repair3.setStatusOfRepair(StatusOfRepair.IN_PROGRESS);
        repairRepository.save(repair3);

        Repair repair4 = new Repair();
        repair4.setCostOfRepair(new BigDecimal(110));
        repair4.setTypeOfRepair(TypeOfRepair.FRAMES);
        repair4.setTypeOfRepair(TypeOfRepair.valueOf("ELECTRICAL_WORK"));
        repair4.setRepairDate(LocalDate.of(2024, 5, 20));

        Admin admin = new Admin();
        admin.setTin("123456789");
        admin.setName("admin");
        admin.setSurname("admin");
        admin.setAddress("admin");
        admin.setPhoneNumber("6971717665");
        admin.setEmail("admin@gmail.com");
        admin.setUsername("admin");
        admin.setPassword(encoder.encode("1234"));
        admin.setActive(true);

        adminRepository.save(admin);

        Faker faker = new Faker();
        for (int i=0; i<numOfIterations; i++){
            PropertyOwner po = createRandomPO(i);
            propertyOwnerRepository.save(po);

            double randomNum = Math.random();

            if (randomNum > 0.5){
                property.setPropertyOwner(po);
                propertyRepository.save(property);
            }else{
                property1.setPropertyOwner(po);
                propertyRepository.save(property1);
            }
        }
        repairRepository.save(repair);
        repairRepository.save(repair1);
        repairRepository.save(repair2);
        repairRepository.save(repair3);
        repairRepository.save(repair4);
    }

    public PropertyOwner createRandomPO(int number){
        Faker faker = new Faker();

        PropertyOwner po = new PropertyOwner();
        po.setTin(faker.number().digits(9));
        po.setName(faker.name().firstName());
        po.setSurname(faker.name().lastName());
        po.setAddress(faker.address().fullAddress());
        po.setPhoneNumber("69" + faker.numerify("########"));
        po.setEmail(faker.internet().emailAddress());
        po.setUsername("user" + number);
        po.setPassword(encoder.encode("1234"));
        po.setActive(true);
        return po;
    }
}
