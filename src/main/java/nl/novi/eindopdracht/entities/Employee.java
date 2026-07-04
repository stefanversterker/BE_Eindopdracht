package nl.novi.eindopdracht.entities;

import jakarta.persistence.Column;

public class Employee extends Person{

    @Column(name = "driver's license")
    private String driversLicense;

    //Getters and setters

    public String getDriversLicense() {
        return driversLicense;
    }

    public void setDriversLicense(String driversLicense) {
        this.driversLicense = driversLicense;
    }
}
