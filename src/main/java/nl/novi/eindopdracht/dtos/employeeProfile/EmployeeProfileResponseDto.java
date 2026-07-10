package nl.novi.eindopdracht.dtos.employeeProfile;

import nl.novi.eindopdracht.enums.DriversLicense;

public class EmployeeProfileResponseDto {

    private Long id;

    private DriversLicense driversLicense;

    private Long personId;

    //Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DriversLicense getDriversLicense() {
        return driversLicense;
    }

    public void setDriversLicense(DriversLicense driversLicense) {
        this.driversLicense = driversLicense;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        personId = personId;
    }
}
