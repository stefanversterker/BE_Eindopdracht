package nl.novi.eindopdracht.repositories;

import nl.novi.eindopdracht.entities.MixerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MixerRepository extends JpaRepository<MixerEntity, Long> {
}
