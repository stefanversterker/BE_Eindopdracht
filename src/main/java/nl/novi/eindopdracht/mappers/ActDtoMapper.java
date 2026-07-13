package nl.novi.eindopdracht.mappers;

import nl.novi.eindopdracht.dtos.act.ActRequestDto;
import nl.novi.eindopdracht.dtos.act.ActResponseDto;
import nl.novi.eindopdracht.entities.ActEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ActDtoMapper implements DtoMapper<ActResponseDto, ActRequestDto, ActEntity> {

    @Override
    public ActResponseDto mapToDto(ActEntity entity){
        var result = new ActResponseDto();
        result.setId(entity.getId());
        result.setName(entity.getName());
        result.setPhone(entity.getPhone());
        result.setEmail(entity.getEmail());
        return result;
    }

    @Override
    public List<ActResponseDto> mapToDto(List<ActEntity> entities) {
        return entities.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public ActEntity mapToEntity(ActRequestDto requestDto){
        var entity = new ActEntity();
        entity.setName(requestDto.getName());
        entity.setPhone(requestDto.getPhone());
        entity.setEmail(requestDto.getEmail());
        return entity;
    }
}
