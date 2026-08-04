package nl.novi.eindopdracht.dtos.channel;

import jakarta.validation.constraints.*;

public class ChannelRequestDto {

    @NotNull(message = "Channel number is required.")
    @Min(value = 1, message = "Channel number must be at least 1.")
    @Max(value = 99, message = "Channel number cannot exceed 99.")
    private Integer number;

    private Long sourceId;

    // Getters and setters

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }
    // I left mixer id and source id out of this dto, I will expose them in the urls

}
