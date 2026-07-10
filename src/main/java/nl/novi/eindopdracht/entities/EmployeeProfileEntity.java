package nl.novi.eindopdracht.entities;

import jakarta.persistence.*;
import nl.novi.eindopdracht.enums.DriversLicense;

@Entity
public class EmployeeProfileEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private DriversLicense driversLicense;

    @OneToOne
    @JoinColumn(
            name = "person_id",
            nullable = false,
            unique = true)
    private PersonEntity personEntity;

    //Getters and setters

    public DriversLicense getDriversLicense() {
        return driversLicense;
    }

    public void setDriversLicense(DriversLicense driversLicense) {
        this.driversLicense = driversLicense;
    }

    public PersonEntity getPersonEntity() {
        return personEntity;
    }

    public void setPersonEntity(PersonEntity personEntity) {
        this.personEntity = personEntity;
    }
}
