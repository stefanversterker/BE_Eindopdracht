package nl.novi.eindopdracht.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instruments")
public class Instrument extends BaseEntity {

    private String name;

    @OneToMany(mappedBy = "instrument")
    private List<PerformerProfile> performerProfiles = new ArrayList<>();

    //Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PerformerProfile> getPerformerProfiles() {
        return performerProfiles;
    }

    public void setPerformerProfiles(List<PerformerProfile> performerProfile) {
        this.performerProfiles = performerProfile;
    }
}
