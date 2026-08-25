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
        return employeeProfileDtoMapper.mapToDto(getEmployeeProfileEntity(id));
    }

    @Transactional
    public EmployeeProfileResponseDto createEmployeeProfile(EmployeeProfileRequestDto employeeProfileRequestDto) {
        // Create the entity the repository expects
        EmployeeProfileEntity employeeProfileEntity = employeeProfileDtoMapper.mapToEntity(employeeProfileRequestDto);

        // Extract personId
        Long personId = employeeProfileRequestDto.getPersonId();

        // Find PersonEntity
        PersonEntity person = getPersonEntity(personId);

        // Set the related person
        employeeProfileEntity.setPersonEntity(person);

        // Save the entity in the repository
        employeeProfileEntity = employeeProfileRepository.save(employeeProfileEntity);

        // Convert the saved entity to a response DTO
        return employeeProfileDtoMapper.mapToDto(employeeProfileEntity);
    }

    @Transactional
    public EmployeeProfileResponseDto updateEmployeeProfile(Long id, EmployeeProfileRequestDto employeeProfileRequestDto) {
        // Retrieve the entity from the database with its current values
        EmployeeProfileEntity existingEmployeeProfileEntity = getEmployeeProfileEntity(id);

        // Get PersonId from requestDto
        Long personId = employeeProfileRequestDto.getPersonId();

        // Get the old person
        PersonEntity newPerson = getPersonEntity(personId);

        existingEmployeeProfileEntity.setPersonEntity(newPerson);

        // Update other fields
        existingEmployeeProfileEntity.setDriversLicense(
                employeeProfileRequestDto.getDriversLicense()
        );

        // Save update to repository
        existingEmployeeProfileEntity = employeeProfileRepository.save(existingEmployeeProfileEntity);

        // Convert the updated entity to a response DTO
        return employeeProfileDtoMapper.mapToDto(existingEmployeeProfileEntity);
    }

    @Transactional
    public void deleteEmployeeProfile(Long id) {
        EmployeeProfileEntity employeeProfile = getEmployeeProfileEntity(id);

        PersonEntity person = employeeProfile.getPersonEntity();

        if (person != null) {
            employeeProfile.setPersonEntity(null);
        }

        employeeProfileRepository.delete(employeeProfile);
    }

    // Helpers
    private EmployeeProfileEntity getEmployeeProfileEntity(Long id) {
        return employeeProfileRepository.findById(id)
                .orElseThrow(() ->
                        new RecordNotFoundException("EmployeeProfile with id " + id + " not found."));
    }

    private PersonEntity getPersonEntity(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() ->
                        new RecordNotFoundException("Person with id " + id + " not found."));
    }


}
