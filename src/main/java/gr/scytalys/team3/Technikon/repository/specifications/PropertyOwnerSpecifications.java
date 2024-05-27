package gr.scytalys.team3.Technikon.repository.specifications;

import gr.scytalys.team3.Technikon.model.PropertyOwner;
import org.springframework.data.jpa.domain.Specification;

public class PropertyOwnerSpecifications {
    public static Specification<PropertyOwner> tinEquals(String tin) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("tin"), tin);
    }

    public static Specification<PropertyOwner> emailEquals(String email) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("email"), email);
    }

    public static Specification<PropertyOwner> usernameEquals(String username) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("username"), username);
    }
}
