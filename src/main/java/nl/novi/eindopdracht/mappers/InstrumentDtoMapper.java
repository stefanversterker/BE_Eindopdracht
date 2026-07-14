package nl.novi.eindopdracht.mappers;

import nl.novi.eindopdracht.dtos.act.ActRequestDto;
import nl.novi.eindopdracht.dtos.act.ActResponseDto;
import nl.novi.eindopdracht.dtos.instrument.InstrumentRequestDto;
import nl.novi.eindopdracht.dtos.instrument.InstrumentResponseDto;
import nl.novi.eindopdracht.entities.ActEntity;
import nl.novi.eindopdracht.entities.InstrumentEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InstrumentDtoMapper implements DtoMapper<InstrumentResponseDto, InstrumentRequestDto, InstrumentEntity> {

    @Override
    public InstrumentResponseDto mapToDto(InstrumentEntity entity){
        var result = new InstrumentResponseDto();
        result.setId(entity.getId());
        result.setName(entity.getName());
        return result;
    }

    @Override
    public List<InstrumentResponseDto> mapToDto(List<InstrumentEntity> entities) {
        return entities.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public InstrumentEntity mapToEntity(InstrumentRequestDto requestDto){
        var entity = new InstrumentEntity();
        entity.setName(requestDto.getName());
        return entity;
    }
}
