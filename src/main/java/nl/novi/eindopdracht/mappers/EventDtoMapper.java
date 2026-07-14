package nl.novi.eindopdracht.mappers;

import nl.novi.eindopdracht.dtos.event.EventRequestDto;
import nl.novi.eindopdracht.dtos.event.EventResponseDto;
import nl.novi.eindopdracht.entities.EventEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventDtoMapper implements DtoMapper<EventResponseDto, EventRequestDto, EventEntity> {

    @Override
    public EventResponseDto mapToDto(EventEntity entity){
        var result = new EventResponseDto();
        result.setId(entity.getId());
        result.setDate(entity.getDate());
        result.setVenue(entity.getVenue());
        return result;
    }

    @Override
    public List<EventResponseDto> mapToDto(List<EventEntity> entities) {
        return entities.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public EventEntity mapToEntity(EventRequestDto requestDto){
        var entity = new EventEntity();
        entity.setDate(requestDto.getDate());
        entity.setVenue(requestDto.getVenue());
        return entity;
    }
}
