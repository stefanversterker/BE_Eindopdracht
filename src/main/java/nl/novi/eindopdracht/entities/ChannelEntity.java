package nl.novi.eindopdracht.entities;

import jakarta.persistence.*;

@Entity
@Table(
        name = "channels",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"mixer_id", "number"}
        )
)

public class ChannelEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "mixer_id")
    private MixerEntity mixerEntity;

    private Integer number;

    @ManyToOne
    @JoinColumn(name = "source_id")
    private SourceEntity sourceEntity;

    // Getters and setters

    public MixerEntity getMixerEntity() {
        return mixerEntity;
    }

    public void setMixerEntity(MixerEntity mixerEntity) {
        this.mixerEntity = mixerEntity;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public SourceEntity getSourceEntity() {
        return sourceEntity;
    }

    public void setSourceEntity(SourceEntity sourceEntity) {
        this.sourceEntity = sourceEntity;
    }



}
