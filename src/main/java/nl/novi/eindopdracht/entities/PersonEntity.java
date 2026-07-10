package nl.novi.eindopdracht.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "persons")
//I used the joined inheritance stategy to nulls in person objects that are not employees. The joined strategy creates separate tables instead.
@Inheritance(strategy = InheritanceType.JOINED)
public class PersonEntity extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    private String phone;

    @OneToOne(mappedBy = "personEntity")
    private PerformerProfileEntity performerProfileEntity;

    @OneToOne(mappedBy = "personEntity")
    private EmployeeProfileEntity employeeProfileEntity;

    @OneToMany(mappedBy = "personEntity")
    private List<EventAssignmentEntity> eventAssignmentEntities = new ArrayList<>();

    //Getters and setters

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public PerformerProfileEntity getPerformerProfile() {
        return performerProfileEntity;
    }

    public void setPerformerProfile(PerformerProfileEntity performerProfileEntity) {
        this.performerProfileEntity = performerProfileEntity;
    }

    public PerformerProfileEntity getPerformerProfileEntity() {
        return performerProfileEntity;
    }

    public void setPerformerProfileEntity(PerformerProfileEntity performerProfileEntity) {
        this.performerProfileEntity = performerProfileEntity;
    }

    public EmployeeProfileEntity getEmployeeProfileEntity() {
        return employeeProfileEntity;
    }

    public void setEmployeeProfileEntity(EmployeeProfileEntity employeeProfileEntity) {
        this.employeeProfileEntity = employeeProfileEntity;
    }

    public List<EventAssignmentEntity> getEventAssignmentEntities() {
        return eventAssignmentEntities;
    }

    public void setEventAssignmentEntities(List<EventAssignmentEntity> eventAssignmentEntities) {
        this.eventAssignmentEntities = eventAssignmentEntities;
    }

    public List<EventAssignmentEntity> getEventAssignments() {
        return eventAssignmentEntities;
    }

    public void setEventAssignments(List<EventAssignmentEntity> eventAssignmentEntities) {
        this.eventAssignmentEntities = eventAssignmentEntities;
    }
}
