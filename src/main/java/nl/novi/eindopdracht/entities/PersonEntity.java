package nl.novi.eindopdracht.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "persons")
public abstract class PersonEntity extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @OneToOne(mappedBy = "person")
    private PerformerProfileEntity performerProfileEntity;

    @OneToMany(mappedBy = "person")
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

    public List<EventAssignmentEntity> getEventAssignments() {
        return eventAssignmentEntities;
    }

    public void setEventAssignments(List<EventAssignmentEntity> eventAssignmentEntities) {
        this.eventAssignmentEntities = eventAssignmentEntities;
    }
}
