package nl.novi.eindopdracht.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PerformerInstrumentEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "performer_profile_id")
    private PerformerProfileEntity performerProfileEntity;

    @ManyToOne
    @JoinColumn(name = "instrument_id")
    private InstrumentEntity instrumentEntity;

    // Getters and Setters

    public PerformerProfileEntity getPerformerProfileEntity() {
        return performerProfileEntity;
    }

    public void setPerformerProfileEntity(PerformerProfileEntity performerProfileEntity) {
        this.performerProfileEntity = performerProfileEntity;
    }

    public InstrumentEntity getInstrumentEntity() {
        return instrumentEntity;
    }

    public void setInstrumentEntity(InstrumentEntity instrumentEntity) {
        this.instrumentEntity = instrumentEntity;
    }


}
