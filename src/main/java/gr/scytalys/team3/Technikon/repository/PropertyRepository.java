package gr.scytalys.team3.Technikon.repository;

import gr.scytalys.team3.Technikon.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
}
