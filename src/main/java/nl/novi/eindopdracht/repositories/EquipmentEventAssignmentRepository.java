package nl.novi.eindopdracht.repositories;

import nl.novi.eindopdracht.entities.EquipmentEventAssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentEventAssignmentRepository extends JpaRepository<EquipmentEventAssignmentEntity, Long> {
}
