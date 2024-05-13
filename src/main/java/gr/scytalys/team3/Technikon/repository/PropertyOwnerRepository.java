package gr.scytalys.team3.Technikon.repository;

import gr.scytalys.team3.Technikon.model.PropertyOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyOwnerRepository extends JpaRepository<PropertyOwner, Long>, JpaSpecificationExecutor<PropertyOwner> {
}
