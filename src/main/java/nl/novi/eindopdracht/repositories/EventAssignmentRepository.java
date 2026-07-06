package nl.novi.eindopdracht.repositories;

import nl.novi.eindopdracht.entities.EventAssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventAssignmentRepository extends JpaRepository<EventAssignmentEntity, Long> {
}
