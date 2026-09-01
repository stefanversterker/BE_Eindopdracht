package nl.novi.eindopdracht.repositories;

import nl.novi.eindopdracht.entities.ChannelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChannelRepository extends JpaRepository<ChannelEntity, Long> {

    List<ChannelEntity> findByMixerEntityId(Long mixerId);

}
