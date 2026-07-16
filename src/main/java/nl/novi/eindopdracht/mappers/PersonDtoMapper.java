package nl.novi.eindopdracht.mappers;

import nl.novi.eindopdracht.dtos.act.ActRequestDto;
import nl.novi.eindopdracht.dtos.act.ActResponseDto;
import nl.novi.eindopdracht.dtos.person.PersonRequestDto;
import nl.novi.eindopdracht.dtos.person.PersonResponseDto;
import nl.novi.eindopdracht.entities.ActEntity;
import nl.novi.eindopdracht.entities.PersonEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonDtoMapper implements DtoMapper<PersonResponseDto, PersonRequestDto, PersonEntity> {

    @Override
    public PersonResponseDto mapToDto(PersonEntity entity){
        var result = new PersonResponseDto();
        result.setId(entity.getId());
        result.setFirstName(entity.getFirstName());
        result.setLastName(entity.getLastName());
        result.setEmail(entity.getEmail());
        result.setPhone(entity.getPhone());
        return result;
    }

    @Override
    public List<PersonResponseDto> mapToDto(List<PersonEntity> entities) {
        return entities.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public PersonEntity mapToEntity(PersonRequestDto requestDto){
        var entity = new PersonEntity();
        entity.setFirstName(requestDto.getFirstName());
        entity.setLastName(requestDto.getLastName());
        entity.setEmail(requestDto.getEmail());
        entity.setPhone(requestDto.getPhone());
        return entity;
    }

}
