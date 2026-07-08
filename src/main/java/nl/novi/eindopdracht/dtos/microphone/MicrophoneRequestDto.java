package nl.novi.eindopdracht.dtos.microphone;

import jakarta.validation.constraints.NotEmpty;
import nl.novi.eindopdracht.dtos.equipment.EquipmentRequestDto;
import nl.novi.eindopdracht.enums.PolarPattern;

import java.util.HashSet;
import java.util.Set;

public class MicrophoneRequestDto extends EquipmentRequestDto {

    @NotEmpty(message = "Polar pattern is required.")
    private Set<PolarPattern> polarPatterns = new HashSet<>();

    private boolean phantomRequired;

    // Getters and Setters


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
