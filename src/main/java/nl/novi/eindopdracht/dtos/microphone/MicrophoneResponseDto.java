package nl.novi.eindopdracht.dtos.microphone;

import nl.novi.eindopdracht.dtos.equipment.EquipmentResponseDto;
import nl.novi.eindopdracht.enums.PolarPattern;

import java.util.HashSet;
import java.util.Set;

public class MicrophoneResponseDto extends EquipmentResponseDto {

    private Long id;

    private Set<PolarPattern> polarPatterns = new HashSet<>();

    private boolean phantomRequired;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
