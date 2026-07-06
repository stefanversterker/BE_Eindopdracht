package nl.novi.eindopdracht.repositories;

import nl.novi.eindopdracht.entities.PerformerActEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformerActRepository extends JpaRepository<PerformerActEntity, Long> {
}
