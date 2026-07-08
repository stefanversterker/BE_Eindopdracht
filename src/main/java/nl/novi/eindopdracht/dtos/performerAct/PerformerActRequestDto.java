package nl.novi.eindopdracht.dtos.performerAct;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PerformerActRequestDto {

    @NotNull(message = "Performer ID is required.")
    private Long performerId;

    @NotNull(message = "Act ID is required.")
    private Long actId;

    @NotBlank(message = "Role is required.")
    @Size(min = 2, max = 50,
            message = "Role must be between 2 and 50 characters.")
    private String role;

    // Getters and Setters

    public Long getPerformerId() {
        return performerId;
    }

    public void setPerformerId(Long performerId) {
        this.performerId = performerId;
    }

    public Long getActId() {
        return actId;
    }

    public void setActId(Long actId) {
        this.actId = actId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
