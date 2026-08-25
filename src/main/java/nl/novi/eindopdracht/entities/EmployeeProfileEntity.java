package nl.novi.eindopdracht.entities;

import jakarta.persistence.*;
import nl.novi.eindopdracht.enums.DriversLicense;

@Entity
@Table(name = "employee_profiles")
public class EmployeeProfileEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private DriversLicense driversLicense;

    @OneToOne
    @JoinColumn(
            name = "person_id",
            nullable = false,
            unique = true)
    private PersonEntity personEntity;

    // Getters and setters

    public DriversLicense getDriversLicense() {
        return driversLicense;
    }

    public void setDriversLicense(DriversLicense driversLicense) {
        this.driversLicense = driversLicense;
    }

    public PersonEntity getPersonEntity() {
        return personEntity;
    }

    // Custom setter ensures that employeeProfile -> person and person -> employeeProfile stay in sync.
    public void setPersonEntity(PersonEntity personEntity) {

        if (this.personEntity != null) {
            this.personEntity.setEmployeeProfileEntity(null);
        }

        this.personEntity = personEntity;

        if (personEntity != null &&
                personEntity.getEmployeeProfileEntity() != this) {
            personEntity.setEmployeeProfileEntity(this);
        }
    }

}
