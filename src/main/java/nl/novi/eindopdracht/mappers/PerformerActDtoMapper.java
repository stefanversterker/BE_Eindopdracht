package nl.novi.eindopdracht.mappers;

import nl.novi.eindopdracht.dtos.performerAct.PerformerActRequestDto;
import nl.novi.eindopdracht.dtos.performerAct.PerformerActResponseDto;
import nl.novi.eindopdracht.entities.PerformerActEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PerformerActDtoMapper implements DtoMapper<PerformerActResponseDto, PerformerActRequestDto, PerformerActEntity> {

    @Override
    public PerformerActResponseDto mapToDto(PerformerActEntity entity){
        var result = new PerformerActResponseDto();
        result.setId(entity.getId());
        result.setPerformerId(entity.getPerformerEntity().getId());
        result.setActId(entity.getActEntity().getId());
        result.setRoles(entity.getRoles());
        return result;
    }

    @Override
    public List<PerformerActResponseDto> mapToDto(List<PerformerActEntity> entities) {
        return entities.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public PerformerActEntity mapToEntity(PerformerActRequestDto requestDto){
        var entity = new PerformerActEntity();
        entity.setRoles(requestDto.getRoles());
        return entity;
    }

}
