package nl.novi.eindopdracht.entities;

import jakarta.persistence.*;

@Entity
@Table(
        name = "equipment_event_assignments",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"equipment_id", "event_id"}
        )
)
public class EquipmentEventAssignmentEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private EquipmentEntity equipmentEntity;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private EventEntity eventEntity;

    // Getters and setters

    public EquipmentEntity getEquipmentEntity() {
        return equipmentEntity;
    }

    public void setEquipment(EquipmentEntity equipmentEntity) {
        this.equipmentEntity = equipmentEntity;
    }

    public EventEntity getEventEntity() {
        return eventEntity;
    }

    public void setEvent(EventEntity eventEntity) {
        this.eventEntity = eventEntity;
    }
}
