package nl.novi.eindopdracht.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "sources")
public class SourceEntity extends BaseEntity {

    private String name;

    @ManyToOne
    @JoinColumn(
            name = "performer_instrument_id",
            nullable = false)
    private PerformerInstrumentEntity performerInstrumentEntity;

    @OneToOne(mappedBy = "sourceEntity")
    private ChannelEntity channelEntity;

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PerformerInstrumentEntity getPerformerInstrumentEntity() {
        return performerInstrumentEntity;
    }

    public void setPerformerInstrumentEntity(PerformerInstrumentEntity performerInstrumentEntity) {
        this.performerInstrumentEntity = performerInstrumentEntity;
    }

    public ChannelEntity getChannelEntity() {
        return channelEntity;
    }

    public void setChannelEntity(ChannelEntity channelEntity) {
        this.channelEntity = channelEntity;
    }
}

