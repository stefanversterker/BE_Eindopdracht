package nl.novi.eindopdracht.dtos.channel;

import jakarta.validation.constraints.*;

public class ChannelRequestDto {

    @NotNull(message = "Channel number is required.")
    @Min(value = 1, message = "Channel number must be at least 1.")
    @Max(value = 99, message = "Channel number cannot exceed 99.")
    private Integer number;

    @NotBlank(message = "Label is required.")
    @Size(min = 2, max = 10,
            message = "Label must be between 2 and 10 characters.")
    private String label;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    // I left mixer id and source id out of this dto, I will expose them in the urls

}
