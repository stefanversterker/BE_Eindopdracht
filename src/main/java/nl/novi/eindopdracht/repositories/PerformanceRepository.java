package nl.novi.eindopdracht.repositories;

import nl.novi.eindopdracht.entities.PerformanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceRepository extends JpaRepository<PerformanceEntity, Long> {
}
