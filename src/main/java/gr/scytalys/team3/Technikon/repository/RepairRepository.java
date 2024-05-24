package gr.scytalys.team3.Technikon.repository;

import gr.scytalys.team3.Technikon.model.Repair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * The repository of the model class Repair.
 * It extends the JPA and also declares 2 custom
 * methods findRepairsByRepairDate and
 * findAllByPropertyPropertyId
 */
@Repository
public interface RepairRepository extends JpaRepository<Repair, Long>, JpaSpecificationExecutor<Repair> {
    /**
     * Get a LocalDate date and search the repairs made in that date.
     * @param repairDate LocalDate value to search repairs.
     * @return A repair Object.
     */
    List<Repair> findRepairsByRepairDate(LocalDate repairDate);
    List<Repair> findAllByPropertyPropertyId(long propertyId);

}
