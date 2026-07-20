package nl.novi.eindopdracht.mappers;

import nl.novi.eindopdracht.dtos.eventAssignment.EventAssignmentRequestDto;
import nl.novi.eindopdracht.dtos.eventAssignment.EventAssignmentResponseDto;
import nl.novi.eindopdracht.entities.EventAssignmentEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventAssignmentDtoMapper implements DtoMapper<EventAssignmentResponseDto, EventAssignmentRequestDto, EventAssignmentEntity> {

    @Override
    public EventAssignmentResponseDto mapToDto(EventAssignmentEntity entity) {
        var result = new EventAssignmentResponseDto();
        result.setId(entity.getId());
        result.setEventId(entity.getEventEntity().getId());
        result.setPersonId(entity.getPersonEntity().getId());
        result.setEventRole(entity.getEventRole());
        return result;
    }

    @Override
    public List<EventAssignmentResponseDto> mapToDto(List<EventAssignmentEntity> entities) {
        return entities.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public EventAssignmentEntity mapToEntity(EventAssignmentRequestDto requestDto){
        var entity = new EventAssignmentEntity();
        entity.setEventRole(requestDto.getEventRole());
        return entity;
    }
}
