package nl.novi.eindopdracht.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "acts")
public class ActEntity extends BaseEntity {

    private String name;

    private String phone;

    private String email;

    @OneToMany(mappedBy = "actEntity")
    private List<PerformerActEntity> performerActEntities = new ArrayList<>();

    @OneToMany(mappedBy = "actEntity")
    private List<PerformanceEntity> performanceEntities = new ArrayList<>();

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<PerformerActEntity> getPerformerActs() {
        return performerActEntities;
    }

    public void setPerformerActs(List<PerformerActEntity> performerActEntities) {
        this.performerActEntities = performerActEntities;
    }

    public List<PerformanceEntity> getPerformances() {
        return performanceEntities;
    }

    public void setPerformances(List<PerformanceEntity> performanceEntities) {
        this.performanceEntities = performanceEntities;
    }

    public List<PerformerActEntity> getPerformerActEntities() {
        return performerActEntities;
    }

    public void setPerformerActEntities(List<PerformerActEntity> performerActEntities) {
        this.performerActEntities = performerActEntities;
    }

    public List<PerformanceEntity> getPerformanceEntities() {
        return performanceEntities;
    }

    public void setPerformanceEntities(List<PerformanceEntity> performanceEntities) {
        this.performanceEntities = performanceEntities;
    }
}
