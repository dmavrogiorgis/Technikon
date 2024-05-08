package gr.scytalys.team3.Technikon.repository;

import gr.scytalys.team3.Technikon.model.Repair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairRepository extends JpaRepository<Repair, Long> {

}
