package nl.novi.eindopdracht.dtos.employeeProfile;

import jakarta.validation.constraints.NotNull;
import nl.novi.eindopdracht.enums.DriversLicense;

public class EmployeeProfileRequestDto {

    @NotNull(message = "Person ID is required.")
    private Long personId;

    private DriversLicense driversLicense;

    // Getters and Setters

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public DriversLicense getDriversLicense() {
        return driversLicense;
    }

    public void setDriversLicense(DriversLicense driversLicense) {
        this.driversLicense = driversLicense;
    }
}
