package gr.scytalys.team3.Technikon.repository;

import gr.scytalys.team3.Technikon.model.Property;
import gr.scytalys.team3.Technikon.model.PropertyOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    List<Property> findPropertiesByPropertyOwner(PropertyOwner propertyOwner);

    @Query("SELECT p FROM Property p WHERE p.propertyOwner.id = :ownerId")
    List<Property> findAllPropertiesByOwnerId(@Param("ownerId") long ownerId);
}
