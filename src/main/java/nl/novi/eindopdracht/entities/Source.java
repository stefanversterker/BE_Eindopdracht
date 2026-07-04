package nl.novi.eindopdracht.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "sources")
public class Source extends BaseEntity{

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "performer_id")
    private PerformerProfile performer;

    @ManyToOne
    @JoinColumn(name = "instrument_id")
    private Instrument instrument;

    //Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PerformerProfile getPerformer() {
        return performer;
    }

    public void setPerformer(PerformerProfile performer) {
        this.performer = performer;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }
}
