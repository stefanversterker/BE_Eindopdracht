package nl.novi.eindopdracht.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "acts")
public class Act extends BaseEntity {

    private String name;

    private String phone;

    private String email;

    @OneToMany(mappedBy = "act")
    private List<PerformerAct> performerActs = new ArrayList<>();

    @OneToMany(mappedBy = "act")
    private List<Performance> performances = new ArrayList<>();

    //Getters and setters


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

    public List<PerformerAct> getPerformerActs() {
        return performerActs;
    }

    public void setPerformerActs(List<PerformerAct> performerActs) {
        this.performerActs = performerActs;
    }

    public List<Performance> getPerformances() {
        return performances;
    }

    public void setPerformances(List<Performance> performances) {
        this.performances = performances;
    }
}
