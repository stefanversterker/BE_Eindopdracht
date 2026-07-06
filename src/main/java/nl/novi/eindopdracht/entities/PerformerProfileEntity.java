package nl.novi.eindopdracht.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "performers")
public class PerformerProfileEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "instrument_id")
    private InstrumentEntity instrumentEntity;

    @OneToMany(mappedBy = "performer")
    private List<PerformerActEntity> acts = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "person_id")
    private PersonEntity personEntity;

    //Getters and setters

    public InstrumentEntity getInstrument() {
        return instrumentEntity;
    }

    public void setInstrument(InstrumentEntity instrumentEntity) {
        this.instrumentEntity = instrumentEntity;
    }

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


}
