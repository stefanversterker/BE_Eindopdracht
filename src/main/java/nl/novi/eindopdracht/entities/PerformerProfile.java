package nl.novi.eindopdracht.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "performers")
public class PerformerProfile extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "instrument_id")
    private Instrument instrument;

    @OneToMany(mappedBy = "performer")
    private List<PerformerAct> acts = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    public List<PerformerAct> getActs() {
        return acts;
    }

    public void setActs(List<PerformerAct> acts) {
        this.acts = acts;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
