package gr.scytalys.team3.Technikon.repository;

import gr.scytalys.team3.Technikon.model.PropertyOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyOwnerRepository extends JpaRepository<PropertyOwner, Long>, JpaSpecificationExecutor<PropertyOwner> {

    @Query("select p.propertyIN from Property p where p.propertyOwner.tin = :tin")
    List<String> findPropertiesByPropertyOwnerTIN(@Param("tin") String tin);
}
