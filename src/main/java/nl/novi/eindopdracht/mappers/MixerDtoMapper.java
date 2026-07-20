package nl.novi.eindopdracht.mappers;

import nl.novi.eindopdracht.dtos.act.ActRequestDto;
import nl.novi.eindopdracht.dtos.act.ActResponseDto;
import nl.novi.eindopdracht.dtos.mixer.MixerRequestDto;
import nl.novi.eindopdracht.dtos.mixer.MixerResponseDto;
import nl.novi.eindopdracht.entities.ActEntity;
import nl.novi.eindopdracht.entities.MixerEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MixerDtoMapper implements DtoMapper<MixerResponseDto, MixerRequestDto, MixerEntity> {

    @Override
    public MixerResponseDto mapToDto(MixerEntity entity){
        var result = new MixerResponseDto();
        result.setId(entity.getId());
        result.setBrand(entity.getBrand());
        result.setModel(entity.getModel());
        return result;
    }

    @Override
    public List<MixerResponseDto> mapToDto(List<MixerEntity> entities) {
        return entities.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public MixerEntity mapToEntity(MixerRequestDto requestDto){
        var entity = new MixerEntity();
        entity.setBrand(requestDto.getBrand());
        entity.setModel(requestDto.getModel());
        return entity;
    }
}
