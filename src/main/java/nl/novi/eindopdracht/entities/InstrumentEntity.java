package nl.novi.eindopdracht.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instruments")
public class InstrumentEntity extends BaseEntity {

    private String name;

    @OneToMany(mappedBy = "instrument")
    private List<PerformerProfileEntity> performerProfileEntities = new ArrayList<>();

    //Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PerformerProfileEntity> getPerformerProfiles() {
        return performerProfileEntities;
    }

    public void setPerformerProfiles(List<PerformerProfileEntity> performerProfileEntity) {
        this.performerProfileEntities = performerProfileEntity;
    }
}
