package nl.novi.eindopdracht.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "performers")
public class PerformerProfileEntity extends BaseEntity {


    @OneToMany(mappedBy = "performerProfileEntity")
    private List<PerformerInstrumentEntity> performerInstruments = new ArrayList<>();

    @OneToMany(mappedBy = "performer")
    private List<PerformerActEntity> acts = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "person_id")
    private PersonEntity personEntity;

    // Getters and Setters

    public List<PerformerActEntity> getActs() {
        return acts;
    }

    public void setActs(List<PerformerActEntity> acts) {
        this.acts = acts;
    }

    public PersonEntity getPerson() {
        return personEntity;
    }

    public void setPerson(PersonEntity personEntity) {
        this.personEntity = personEntity;
    }

    public List<PerformerInstrumentEntity> getPerformerInstruments() {
        return performerInstruments;
    }

    public void setPerformerInstruments(List<PerformerInstrumentEntity> performerInstruments) {
        this.performerInstruments = performerInstruments;
    }

    public PersonEntity getPersonEntity() {
        return personEntity;
    }

    public void setPersonEntity(PersonEntity personEntity) {
        this.personEntity = personEntity;
    }
}
