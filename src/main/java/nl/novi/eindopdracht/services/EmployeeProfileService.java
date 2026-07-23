package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.dtos.employeeProfile.EmployeeProfileRequestDto;
import nl.novi.eindopdracht.dtos.employeeProfile.EmployeeProfileResponseDto;
import nl.novi.eindopdracht.entities.EmployeeProfileEntity;
import nl.novi.eindopdracht.entities.PersonEntity;
import nl.novi.eindopdracht.exceptions.RecordNotFoundException;
import nl.novi.eindopdracht.mappers.EmployeeProfileDtoMapper;
import nl.novi.eindopdracht.repositories.EmployeeProfileRepository;
import nl.novi.eindopdracht.repositories.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeProfileService {

    private final EmployeeProfileRepository employeeProfileRepository;
    private final EmployeeProfileDtoMapper employeeProfileDtoMapper;
    private final PersonRepository personRepository;

    public EmployeeProfileService(EmployeeProfileRepository employeeProfileRepository, EmployeeProfileDtoMapper employeeProfileDtoMapper, PersonRepository personRepository) {
        this.employeeProfileRepository = employeeProfileRepository;
        this.employeeProfileDtoMapper = employeeProfileDtoMapper;
        this.personRepository = personRepository;
    }

    @Transactional(readOnly = true)
    public List<EmployeeProfileResponseDto> getAllEmployeeProfiles() {
        return employeeProfileDtoMapper.mapToDto(employeeProfileRepository.findAll());
    }

    @Transactional(readOnly = true)
    public EmployeeProfileResponseDto getEmployeeProfileById(long id) {
        EmployeeProfileEntity entity = employeeProfileRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("EmployeeProfile with id " + id + " not found."));

        return employeeProfileDtoMapper.mapToDto(entity);
    }

    public EmployeeProfileResponseDto createEmployeeProfile(EmployeeProfileRequestDto employeeProfileRequestDto) {
        // Create the entity the repository expects
        EmployeeProfileEntity employeeProfileEntity = employeeProfileDtoMapper.mapToEntity(employeeProfileRequestDto);
        // Extract personId
        Long personId = employeeProfileRequestDto.getPersonId();
        // Find PersonEntity
        PersonEntity person = personRepository.findById(personId)
                .orElseThrow(() -> new RecordNotFoundException("Person with id " + personId + " not found."));
        // Set the related person
        employeeProfileEntity.setPersonEntity(person);
        // Save the entity in the repository
        employeeProfileEntity = employeeProfileRepository.save(employeeProfileEntity);
        // Convert the saved entity to a response DTO
        return employeeProfileDtoMapper.mapToDto(employeeProfileEntity);
    }

    public EmployeeProfileResponseDto updateEmployeeProfile(Long id, EmployeeProfileRequestDto employeeProfileRequestDto) {
        // Retrieve the entity from the database with its current values
        EmployeeProfileEntity existingEmployeeProfileEntity = getEmployeeProfileEntity(id);

        // Get PersonId from requestDto
        Long personId = employeeProfileRequestDto.getPersonId();

        // Get PersonEntity from repository
        PersonEntity person = personRepository.findById(personId)
                .orElseThrow(() ->
                        new RecordNotFoundException("Person with id " + personId + " not found."));

        // Update Person field
        existingEmployeeProfileEntity.setPersonEntity(person);
        existingEmployeeProfileEntity.setDriversLicense(employeeProfileRequestDto.getDriversLicense());


        // Save update to repository
        existingEmployeeProfileEntity = employeeProfileRepository.save(existingEmployeeProfileEntity);

        // Convert the updated entity to a response DTO
        return employeeProfileDtoMapper.mapToDto(existingEmployeeProfileEntity);
    }

    public void deleteEmployeeProfile(Long id) {
        EmployeeProfileEntity employeeProfile = getEmployeeProfileEntity(id);
        employeeProfileRepository.delete(employeeProfile);
    }

    // Helper: gets entity from repository
    private EmployeeProfileEntity getEmployeeProfileEntity(Long id) {
        EmployeeProfileEntity employeeProfileEntity = employeeProfileRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("EmployeeProfile with id " + id + " not found."));
        return employeeProfileEntity;
    }
}
