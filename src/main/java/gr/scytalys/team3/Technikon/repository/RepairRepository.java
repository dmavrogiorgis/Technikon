package gr.scytalys.team3.Technikon.repository;

import gr.scytalys.team3.Technikon.model.Repair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RepairRepository extends JpaRepository<Repair, Long>, JpaSpecificationExecutor<Repair> {
    List<Repair> findRepairsByRepairDate(LocalDate repairDate);
    List<Repair> findAllByPropertyPropertyId(long propertyId);

}
