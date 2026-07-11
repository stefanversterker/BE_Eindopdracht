package nl.novi.eindopdracht.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class PerformerInstrumentEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "performer_profile_id")
    private PerformerProfileEntity performerProfileEntity;

    @ManyToOne
    @JoinColumn(name = "instrument_id")
    private InstrumentEntity instrumentEntity;

    @OneToMany(mappedBy = "performerInstrumentEntity")
    private List<SourceEntity> sources = new ArrayList<>();

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

    public List<SourceEntity> getSources() {
        return sources;
    }

    public void setSources(List<SourceEntity> sources) {
        this.sources = sources;
    }
}
