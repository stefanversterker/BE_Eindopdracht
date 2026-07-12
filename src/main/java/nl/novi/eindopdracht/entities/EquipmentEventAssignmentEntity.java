package nl.novi.eindopdracht.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "EquipmentEventAssignments")
public class EquipmentEventAssignmentEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private EquipmentEntity equipmentEntity;

    @ManyToOne
    @JoinColumn(name = "event_entity")
    private EventEntity eventEntity;

    // Getters and setters

    public EquipmentEntity getEquipmentEntity() {
        return equipmentEntity;
    }

    public void setEquipmentEntity(EquipmentEntity equipmentEntity) {
        this.equipmentEntity = equipmentEntity;
    }

    public EventEntity getEventEntity() {
        return eventEntity;
    }

    public void setEventEntity(EventEntity eventEntity) {
        this.eventEntity = eventEntity;
    }
}
