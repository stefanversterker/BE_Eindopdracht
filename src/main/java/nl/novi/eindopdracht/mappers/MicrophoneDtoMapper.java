package nl.novi.eindopdracht.mappers;

import nl.novi.eindopdracht.dtos.microphone.MicrophoneRequestDto;
import nl.novi.eindopdracht.dtos.microphone.MicrophoneResponseDto;
import nl.novi.eindopdracht.entities.MicrophoneEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MicrophoneDtoMapper implements DtoMapper<MicrophoneResponseDto, MicrophoneRequestDto, MicrophoneEntity> {

    @Override
    public MicrophoneResponseDto mapToDto(MicrophoneEntity entity){
        var result = new MicrophoneResponseDto();
        result.setId(entity.getId());
        result.setBrand(entity.getBrand());
        result.setModel(entity.getModel());
        result.setPolarPatterns(entity.getPolarPatterns());
        result.setPhantomRequired(entity.isPhantomRequired());
        return result;
    }

    @Override
    public List<MicrophoneResponseDto> mapToDto(List<MicrophoneEntity> entities) {
        return entities.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public MicrophoneEntity mapToEntity(MicrophoneRequestDto requestDto){
        var entity = new MicrophoneEntity();
        entity.setBrand(requestDto.getBrand());
        entity.setModel(requestDto.getModel());
        entity.setPolarPatterns(requestDto.getPolarPatterns());
        entity.setPhantomRequired(requestDto.isPhantomRequired());
        return entity;
    }

}
