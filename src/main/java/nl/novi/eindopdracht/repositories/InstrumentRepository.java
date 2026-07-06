package nl.novi.eindopdracht.repositories;

import nl.novi.eindopdracht.entities.InstrumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstrumentRepository extends JpaRepository<InstrumentEntity, Long> {
}
