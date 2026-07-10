package nl.novi.eindopdracht.repositories;

import nl.novi.eindopdracht.entities.EmployeeProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeProfileEntity, Long> {
}
