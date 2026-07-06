package nl.novi.eindopdracht.repositories;

import nl.novi.eindopdracht.entities.MicrophoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MicrophoneRepository extends JpaRepository<MicrophoneEntity, Long> {
}
