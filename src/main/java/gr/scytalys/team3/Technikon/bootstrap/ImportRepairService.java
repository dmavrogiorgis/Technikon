package gr.scytalys.team3.Technikon.bootstrap;

import com.github.javafaker.Faker;
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

@AllArgsConstructor
@Configuration
@Slf4j
public class ImportRepairService {
    private final RepairRepository repairRepository;
    private final PropertyOwnerRepository propertyOwnerRepository;
    private final PropertyRepository propertyRepository;

    private final static int numOfIterations = 20;

    @Bean
    public CommandLineRunner myCommandLineRunner(){
        return this::run;
    }

    private void run(String... args) {
        Faker faker = new Faker();

        Property property = new Property();
        property.setPropertyIN(Long.parseLong(faker.number().digits(9)));

        Property property1 = new Property();
        property.setPropertyIN(Long.parseLong(faker.number().digits(9)));

        Repair repair = new Repair();
        repair.setCostOfRepair(new BigDecimal(100));
        repair.setTypeOfRepair(TypeOfRepair.PAINTING);
        repair.setTypeOfRepair(TypeOfRepair.valueOf("PAINTING"));
        repair.setRepairDate(LocalDate.of(2024, 5, 14));
        repair.setProperty(property);

        Repair repair1 = new Repair();
        repair1.setCostOfRepair(new BigDecimal(300));
        repair1.setTypeOfRepair(TypeOfRepair.INSULATION);
        repair1.setTypeOfRepair(TypeOfRepair.valueOf("INSULATION"));
        repair1.setRepairDate(LocalDate.of(2024, 5, 15));
        repair1.setProperty(property);

        Repair repair2 = new Repair();
        repair2.setCostOfRepair(new BigDecimal(400));
        repair2.setTypeOfRepair(TypeOfRepair.ELECTRICAL_WORK);
        repair2.setTypeOfRepair(TypeOfRepair.valueOf("FRAMES"));
        repair2.setRepairDate(LocalDate.of(2024, 5, 17));
        repair.setProperty(property1);

        Repair repair3 = new Repair();
        repair3.setCostOfRepair(new BigDecimal(150));
        repair3.setTypeOfRepair(TypeOfRepair.PLUMBING);
        repair3.setTypeOfRepair(TypeOfRepair.valueOf("PLUMBING"));
        repair3.setRepairDate(LocalDate.of(2024, 5, 19));

        Repair repair4 = new Repair();
        repair4.setCostOfRepair(new BigDecimal(110));
        repair4.setTypeOfRepair(TypeOfRepair.FRAMES);
        repair4.setTypeOfRepair(TypeOfRepair.valueOf("ELECTRICAL_WORK"));
        repair4.setRepairDate(LocalDate.of(2024, 5, 20));

        for (int i=0; i<numOfIterations; i++){
            PropertyOwner po = createRandomPO();
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

    public PropertyOwner createRandomPO(){
        Faker faker = new Faker();

        PropertyOwner po = new PropertyOwner();
        po.setTin(faker.number().digits(9));
        po.setName(faker.name().firstName());
        po.setSurname(faker.name().lastName());
        po.setAddress(faker.address().fullAddress());
        po.setPhoneNumber("69" + faker.numerify("##########"));
        po.setEmail(faker.internet().emailAddress());
        po.setUsername(faker.name().username());
        po.setPassword(faker.internet().password());
        po.setActive(true);
        return po;
    }
}
