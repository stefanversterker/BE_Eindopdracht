package nl.novi.eindopdracht.mappers;

import nl.novi.eindopdracht.dtos.equipmentEventAssignment.EquipmentEventAssignmentRequestDto;
import nl.novi.eindopdracht.dtos.equipmentEventAssignment.EquipmentEventAssignmentResponseDto;
import nl.novi.eindopdracht.entities.EquipmentEventAssignmentEntity;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EquipmentEventAssignmentDtoMapper implements DtoMapper<EquipmentEventAssignmentResponseDto, EquipmentEventAssignmentRequestDto, EquipmentEventAssignmentEntity> {

    @Override
    public EquipmentEventAssignmentResponseDto mapToDto(EquipmentEventAssignmentEntity entity){
        var result = new EquipmentEventAssignmentResponseDto();
        result.setId(entity.getId());
        result.setEquipmentId(entity.getEquipmentEntity().getId());
        result.setEventId(entity.getEventEntity().getId());
        return result;
    }

    @Override
    public List<EquipmentEventAssignmentResponseDto> mapToDto(List<EquipmentEventAssignmentEntity> entities) {
        return entities.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public EquipmentEventAssignmentEntity mapToEntity(
            EquipmentEventAssignmentRequestDto dto) {

        var entity = new EquipmentEventAssignmentEntity();

        return entity;
    }
}
