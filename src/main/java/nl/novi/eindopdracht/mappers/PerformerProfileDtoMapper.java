package nl.novi.eindopdracht.mappers;

import nl.novi.eindopdracht.dtos.performerProfile.PerformerProfileRequestDto;
import nl.novi.eindopdracht.dtos.performerProfile.PerformerProfileResponseDto;
import nl.novi.eindopdracht.entities.PerformerProfileEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PerformerProfileDtoMapper implements DtoMapper<PerformerProfileResponseDto, PerformerProfileRequestDto, PerformerProfileEntity> {

    @Override
    public PerformerProfileResponseDto mapToDto(PerformerProfileEntity entity){
        var result = new PerformerProfileResponseDto();
        result.setId(entity.getId());
        result.setPersonId(entity.getPersonEntity().getId());
        return result;
    }

    @Override
    public List<PerformerProfileResponseDto> mapToDto(List<PerformerProfileEntity> entities) {
        return entities.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public PerformerProfileEntity mapToEntity(PerformerProfileRequestDto requestDto){
        var entity = new PerformerProfileEntity();
        return entity;
    }

}
