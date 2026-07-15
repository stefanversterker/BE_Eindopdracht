package nl.novi.eindopdracht.dtos.performerAct;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class PerformerActRequestDto {

    @NotNull(message = "Performer ID is required.")
    private Long performerId;

    @NotNull(message = "Act ID is required.")
    private Long actId;

    @NotEmpty(message = "At least one role is required.")
    private Set<
            @NotBlank(message = "Role cannot be blank.")
            @Size(
                    min = 2,
                    max = 50,
                    message = "Role must be between 2 and 50 characters."
            )
                    String
            > roles;

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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
