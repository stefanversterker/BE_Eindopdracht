package nl.novi.eindopdracht.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instruments")
public class InstrumentEntity extends BaseEntity {

    private String name;

    @OneToMany(mappedBy = "instrumentEntity")
    private List<PerformerInstrumentEntity> performerInstruments = new ArrayList<>();

    //Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PerformerInstrumentEntity> getPerformerInstruments() {
        return performerInstruments;
    }

    public void setPerformerInstruments(List<PerformerInstrumentEntity> performerInstruments) {
        this.performerInstruments = performerInstruments;
    }


}
