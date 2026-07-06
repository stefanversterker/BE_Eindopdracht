package nl.novi.eindopdracht.repositories;

import nl.novi.eindopdracht.entities.ActEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActRepository  extends JpaRepository<ActEntity, Long> {
}
