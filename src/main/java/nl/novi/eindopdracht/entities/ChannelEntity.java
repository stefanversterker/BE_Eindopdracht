package nl.novi.eindopdracht.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "channels")
public class ChannelEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "mixer_id")
    private MixerEntity mixerEntity;

    private Integer number;

    private String label;

    @ManyToOne
    @JoinColumn(name = "source_id")
    private SourceEntity sourceEntity;


    //Getters and setters

    public MixerEntity getMixer() {
        return mixerEntity;
    }

    public void setMixer(MixerEntity mixerEntity) {
        this.mixerEntity = mixerEntity;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public SourceEntity getSource() {
        return sourceEntity;
    }

    public void setSource(SourceEntity sourceEntity) {
        this.sourceEntity = sourceEntity;
    }

}
