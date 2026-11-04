package nl.novi.eindopdracht.repositories;

import nl.novi.eindopdracht.entities.PerformerInstrumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformerInstrumentRepository extends JpaRepository<PerformerInstrumentEntity, Long> {

    boolean existsByPerformerProfileEntityIdAndInstrumentEntityId(
            Long performerProfileId,
            Long instrumentId);

    boolean existsByInstrumentEntityId(Long id);

}
