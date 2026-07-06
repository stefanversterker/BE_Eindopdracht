package nl.novi.eindopdracht.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "sources")
public class SourceEntity extends BaseEntity{

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "performer_id")
    private PerformerProfileEntity performer;

    @ManyToOne
    @JoinColumn(name = "instrument_id")
    private InstrumentEntity instrumentEntity;

    //Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PerformerProfileEntity getPerformer() {
        return performer;
    }

    public void setPerformer(PerformerProfileEntity performer) {
        this.performer = performer;
    }

    public InstrumentEntity getInstrument() {
        return instrumentEntity;
    }

    public void setInstrument(InstrumentEntity instrumentEntity) {
        this.instrumentEntity = instrumentEntity;
    }
}
