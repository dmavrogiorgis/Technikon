package gr.scytalys.team3.Technikon.bootstrap;

import com.github.javafaker.Faker;
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
private final int numOfIterations=1;
    @Bean
    public CommandLineRunner myCommandLineRunner(){
        return this::run;
    }

    private void run(String... args) {
        Repair repair = new Repair();
        repair.setCostOfRepair(new BigDecimal(100));
        repair.setTypeOfRepair(TypeOfRepair.INSULATION);
        repairRepository.save(repair);

        Repair repair1 = new Repair();
        repair1.setCostOfRepair(new BigDecimal(300));
        repair1.setTypeOfRepair(TypeOfRepair.FRAMES);
        repairRepository.save(repair1);

        Repair repair2 = new Repair();
        repair2.setCostOfRepair(new BigDecimal(400));
        repair2.setTypeOfRepair(TypeOfRepair.PAINTING);
        repairRepository.save(repair2);

        Repair repair3 = new Repair();
        repair3.setCostOfRepair(new BigDecimal(150));
        repair3.setTypeOfRepair(TypeOfRepair.PLUMBING);
        repairRepository.save(repair3);

        Repair repair4 = new Repair();
        repair4.setCostOfRepair(new BigDecimal(110));
        repair4.setTypeOfRepair(TypeOfRepair.ELECTRICAL_WORK);
        repairRepository.save(repair4);
        //Create 1 owner with no properties
        PropertyOwner po = createRandomPO();
        propertyOwnerRepository.save(po);

        //Create 1 owner with 1 property and 1 repair for that property

        PropertyOwner po3 = createRandomPO();
        propertyOwnerRepository.save(po3);
        Property p3 = createRandomProp(po3, 21L);

        Faker faker = new Faker();
        for (int i=0; i<numOfIterations; i++) {
            PropertyOwner po2 = createRandomPO();
            propertyOwnerRepository.save(po);

            double randomNum = Math.random();

            if (randomNum > 0.5) {
                Property property = new Property();
                property.setPropertyIN(Long.parseLong(faker.number().digits(9)));
                property.setPropertyOwner(po);
                propertyRepository.save(property);
            }
        }
        PropertyOwner po4 = createRandomPO();
        propertyOwnerRepository.save(po4);
        //Create 1 owner with 3 properties,1 property has 0 repairs, 1 property has 1 repair, 1 property has 3 repairs
        for(int j=0; j<3; j++){
            Property newProp= createRandomProp(po4, (long) j);
            propertyRepository.save(newProp);
            if(j==0) {
                Repair repair01 = new Repair();
                repair.setTypeOfRepair(TypeOfRepair.FRAMES); // Replace with your desired type
                repair.setDescription("Fixing broken circuit"); // Replace with a description
                repair.setCostOfRepair(BigDecimal.valueOf(150.0)); // Replace with the cost
                repair.setRepairDate(LocalDate.of(2024, 5, 22)); // Replace with the repair date
                repair.setStatusOfRepair(StatusOfRepair.IN_PROGRESS); // Replace with the status
                repair.setActive(true);
                // Set the associated property (you'll need to create a Property object)
                repair.setProperty(createRandomProp(createRandomPO(),12L));
                repairRepository.save(repair01);
            } else if (j==1) {
                Repair repair02 = new Repair();
                repair.setTypeOfRepair(TypeOfRepair.ELECTRICAL_WORK); // Replace with your desired type
                repair.setDescription("Fixing broken circuit"); // Replace with a description
                repair.setCostOfRepair(BigDecimal.valueOf(13333333)); // Replace with the cost
                repair.setRepairDate(LocalDate.of(2024, 5, 22)); // Replace with the repair date
                repair.setStatusOfRepair(StatusOfRepair.IN_PROGRESS); // Replace with the status
                repair.setActive(true);
                repair.setProperty(createRandomProp(createRandomPO(),12L));
                repairRepository.save(repair02);
            }
        }
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
    public Property createRandomProp(PropertyOwner propertyOwner, Long id){
        Faker faker = new Faker();

        Property property = new Property();
        property.setPropertyId(id);
        property.setPropertyIN(767676760+id);
        property.setPropertyOwner(propertyOwner);
        property.setYearOfConstruct(faker.number().numberBetween(1999,2020));
        property.setTypeOfProperty(TypeOfProperty.APARTMENT);
        property.setActive(true);
        return property;
    }
}
