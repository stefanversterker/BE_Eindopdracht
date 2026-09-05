package nl.novi.eindopdracht.dtos.mixer;

import nl.novi.eindopdracht.dtos.equipment.EquipmentResponseDto;

public class MixerResponseDto extends EquipmentResponseDto {

    private Long imageId;

    // Getters and Setters


    @Override
    public Long getImageId() {
        return imageId;
    }

    @Override
    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }
}
