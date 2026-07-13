package nl.novi.eindopdracht.mappers;

import nl.novi.eindopdracht.dtos.channel.ChannelRequestDto;
import nl.novi.eindopdracht.dtos.channel.ChannelResponseDto;
import nl.novi.eindopdracht.entities.ChannelEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChannelDtoMapper implements DtoMapper<ChannelResponseDto, ChannelRequestDto, ChannelEntity> {

    @Override
    public ChannelResponseDto mapToDto(ChannelEntity entity){
        var result = new ChannelResponseDto();
        result.setId(entity.getId());
        result.setNumber(entity.getNumber());
        result.setLabel(entity.getLabel());
        result.setMixerId(entity.getMixerEntity().getId());
        // Users should be able to create a channel without filling in the source name. Null should be allowed.
        result.setSourceId(
                entity.getSourceEntity() != null
                        ? entity.getSourceEntity().getId()
                        : null
        );
        result.setSourceName(
                entity.getSourceEntity() != null
                        ? entity.getSourceEntity().getName()
                        : null
        );
        return result;
    }

    @Override
    public List<ChannelResponseDto> mapToDto(List<ChannelEntity> entities) {
        return entities.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public ChannelEntity mapToEntity(ChannelRequestDto requestDto){
        var entity = new ChannelEntity();
        entity.setNumber(requestDto.getNumber());
        entity.setLabel(requestDto.getLabel());
        return entity;
    }
}
