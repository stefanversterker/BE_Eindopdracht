package nl.novi.eindopdracht.mappers;

import nl.novi.eindopdracht.dtos.source.SourceRequestDto;
import nl.novi.eindopdracht.dtos.source.SourceResponseDto;
import nl.novi.eindopdracht.entities.SourceEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SourceDtoMapper implements DtoMapper<SourceResponseDto, SourceRequestDto, SourceEntity> {

    @Override
    public SourceResponseDto mapToDto(SourceEntity entity){
        var result = new SourceResponseDto();
        result.setId(entity.getId());
        result.setName(entity.getName());
        result.setPerformerInstrumentId(entity.getPerformerInstrumentEntity().getId());
        return result;
    }

    @Override
    public List<SourceResponseDto> mapToDto(List<SourceEntity> entities) {
        return entities.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public SourceEntity mapToEntity(SourceRequestDto requestDto){
        var entity = new SourceEntity();
        entity.setName(requestDto.getName());
        return entity;
    }
}
