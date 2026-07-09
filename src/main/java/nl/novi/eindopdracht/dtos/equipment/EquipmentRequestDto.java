package nl.novi.eindopdracht.dtos.equipment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EquipmentRequestDto {

    @NotBlank(message = "Brand is required.")
    @Size(min = 2, max = 50,
            message = "Brand must be between 2 and 50 characters.")
    private String brand;

    @NotBlank(message = "Model is required.")
    @Size(min = 2, max = 50,
            message = "Model must be between 2 and 50 characters.")
    private String model;

    // Getters and Setters

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
