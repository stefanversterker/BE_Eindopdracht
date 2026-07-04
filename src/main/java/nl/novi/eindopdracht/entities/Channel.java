package nl.novi.eindopdracht.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "channels")
public class Channel extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "mixer_id")
    private Mixer mixer;


    private Integer number;

    private String label;

    @ManyToOne
    @JoinColumn(name = "source")
    private Source source;


    //Getters and setters

    public Mixer getMixer() {
        return mixer;
    }

    public void setMixer(Mixer mixer) {
        this.mixer = mixer;
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

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

}
