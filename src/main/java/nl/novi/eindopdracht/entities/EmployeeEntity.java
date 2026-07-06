package nl.novi.eindopdracht.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import nl.novi.eindopdracht.enums.DriversLicense;

@Entity
public class EmployeeEntity extends PersonEntity {

    @Column(name = "driver's license")
    @Enumerated(EnumType.STRING)
    private DriversLicense driversLicense;

    //Getters and setters

    public DriversLicense getDriversLicense() {
        return driversLicense;
    }

    public void setDriversLicense(DriversLicense driversLicense) {
        this.driversLicense = driversLicense;
    }
}
