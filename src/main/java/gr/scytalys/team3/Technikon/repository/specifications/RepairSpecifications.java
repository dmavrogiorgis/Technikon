package gr.scytalys.team3.Technikon.repository.specifications;

import gr.scytalys.team3.Technikon.model.Repair;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class RepairSpecifications {

    public static Specification<Repair> repairDateBetween(LocalDate startDate, LocalDate endDate){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("repairDate"), startDate , endDate));
    }
    public static Specification<Repair> repairIsActive(){
        return (root, query, criteriaBuilder) -> criteriaBuilder.isTrue(root.get("isActive"));
    }
}
