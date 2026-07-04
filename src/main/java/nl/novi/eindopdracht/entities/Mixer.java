package nl.novi.eindopdracht.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mixers")
public class Mixer extends Equipment {

    @OneToMany(mappedBy = "mixer")
    private List<Channel> channels = new ArrayList<>();

    //Getters and setters

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channel) {
        this.channels = channel;
    }
}
