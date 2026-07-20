package nl.novi.eindopdracht.mappers;

import nl.novi.eindopdracht.dtos.employeeProfile.EmployeeProfileRequestDto;
import nl.novi.eindopdracht.dtos.employeeProfile.EmployeeProfileResponseDto;
import nl.novi.eindopdracht.entities.EmployeeProfileEntity;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeProfileDtoMapper implements DtoMapper<EmployeeProfileResponseDto, EmployeeProfileRequestDto, EmployeeProfileEntity> {

    @Override
    public EmployeeProfileResponseDto mapToDto(EmployeeProfileEntity entity){
        var result = new EmployeeProfileResponseDto();
        result.setId(entity.getId());
        result.setDriversLicense(entity.getDriversLicense());
        result.setPersonId(entity.getPersonEntity().getId());
        return result;
    }

    @Override
    public List<EmployeeProfileResponseDto> mapToDto(List<EmployeeProfileEntity> entities) {
        return entities.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeProfileEntity mapToEntity(EmployeeProfileRequestDto requestDto){
        var entity = new EmployeeProfileEntity();
        entity.setDriversLicense(requestDto.getDriversLicense());
        return entity;
    }

}
