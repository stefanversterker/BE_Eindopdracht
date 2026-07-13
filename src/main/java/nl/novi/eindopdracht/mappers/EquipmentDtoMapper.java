package nl.novi.eindopdracht.mappers;

import nl.novi.eindopdracht.dtos.equipment.EquipmentRequestDto;
import nl.novi.eindopdracht.dtos.equipment.EquipmentResponseDto;
import nl.novi.eindopdracht.entities.EquipmentEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EquipmentDtoMapper implements DtoMapper<EquipmentResponseDto, EquipmentRequestDto, EquipmentEntity> {

    @Override
    public EquipmentResponseDto mapToDto(EquipmentEntity entity){
        var result = new EquipmentResponseDto();
        result.setId(entity.getId());
        result.setBrand(entity.getBrand());
        result.setModel(entity.getModel());
        return result;
    }

    @Override
    public List<EquipmentResponseDto> mapToDto(List<EquipmentEntity> entities) {
        return entities.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public EquipmentEntity mapToEntity(EquipmentRequestDto requestDto){
        var entity = new EquipmentEntity();
        entity.setBrand(requestDto.getBrand());
        entity.setModel(requestDto.getModel());
        return entity;
    }

}
