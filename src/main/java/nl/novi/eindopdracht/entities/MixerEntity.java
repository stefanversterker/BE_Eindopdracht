package nl.novi.eindopdracht.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class MixerEntity extends EquipmentEntity {

    @OneToMany(mappedBy = "mixerEntity")
    private List<ChannelEntity> channelEntities = new ArrayList<>();

    // Getters and Setters

    public List<ChannelEntity> getChannels() {
        return channelEntities;
    }

    public void setChannels(List<ChannelEntity> channelEntity) {
        this.channelEntities = channelEntity;
    }
}
