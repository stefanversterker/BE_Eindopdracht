package nl.novi.eindopdracht.entities;

import jakarta.persistence.*;
import nl.novi.eindopdracht.enums.PolarPattern;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class MicrophoneEntity extends EquipmentEntity {

    @ElementCollection(targetClass = PolarPattern.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "microphone_polar_patterns",
            joinColumns = @JoinColumn(name = "microphone_id")
    )
    @Column(name = "polar_pattern")
    private Set<PolarPattern> polarPatterns = new HashSet<>();

    private boolean phantomRequired;

    //Getters and setters

    public Set<PolarPattern> getPolarPatterns() {
        return polarPatterns;
    }

    public void setPolarPatterns(Set<PolarPattern> polarPatterns) {
        this.polarPatterns = polarPatterns;
    }

    public boolean isPhantomRequired() {
        return phantomRequired;
    }

    public void setPhantomRequired(boolean phantomRequired) {
        this.phantomRequired = phantomRequired;
    }

}