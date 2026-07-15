package nl.novi.eindopdracht.mappers;

import nl.novi.eindopdracht.dtos.performerInstrument.PerformerInstrumentRequestDto;
import nl.novi.eindopdracht.dtos.performerInstrument.PerformerInstrumentResponseDto;
import nl.novi.eindopdracht.entities.PerformerInstrumentEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PerformerInstrumentDtoMapper implements DtoMapper<PerformerInstrumentResponseDto, PerformerInstrumentRequestDto, PerformerInstrumentEntity> {

    @Override
    public PerformerInstrumentResponseDto mapToDto(PerformerInstrumentEntity entity){
        var result = new PerformerInstrumentResponseDto();
        result.setId(entity.getId());
        result.setPerformerProfileId(entity.getPerformerProfileEntity().getId());
        result.setInstrumentId(entity.getInstrumentEntity().getId());
        return result;
    }

    @Override
    public List<PerformerInstrumentResponseDto> mapToDto(List<PerformerInstrumentEntity> entities) {
        return entities.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public PerformerInstrumentEntity mapToEntity(
            PerformerInstrumentRequestDto requestDto) {
        return new PerformerInstrumentEntity();
    }
}
