package nl.novi.eindopdracht.mappers;

import nl.novi.eindopdracht.dtos.performance.PerformanceRequestDto;
import nl.novi.eindopdracht.dtos.performance.PerformanceResponseDto;
import nl.novi.eindopdracht.entities.PerformanceEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PerformanceDtoMapper  implements DtoMapper<PerformanceResponseDto, PerformanceRequestDto, PerformanceEntity>{

    @Override
    public PerformanceResponseDto mapToDto(PerformanceEntity entity){
        var result = new PerformanceResponseDto();
        result.setId(entity.getId());
        result.setEventId(entity.getEventEntity().getId());
        result.setActId(entity.getActEntity().getId());
        return result;
    }

    @Override
    public List<PerformanceResponseDto> mapToDto(List<PerformanceEntity> entities) {
        return entities.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public PerformanceEntity mapToEntity(PerformanceRequestDto requestDto){
        var entity = new PerformanceEntity();

        return entity;
    }

}
