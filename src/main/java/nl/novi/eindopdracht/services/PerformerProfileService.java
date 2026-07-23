package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.dtos.performerProfile.PerformerProfileRequestDto;
import nl.novi.eindopdracht.dtos.performerProfile.PerformerProfileResponseDto;
import nl.novi.eindopdracht.entities.PerformerProfileEntity;
import nl.novi.eindopdracht.entities.PersonEntity;
import nl.novi.eindopdracht.exceptions.RecordNotFoundException;
import nl.novi.eindopdracht.mappers.PerformerProfileDtoMapper;
import nl.novi.eindopdracht.repositories.PerformerProfileRepository;
import nl.novi.eindopdracht.repositories.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PerformerProfileService {

    private final PerformerProfileRepository performerProfileRepository;
    private final PerformerProfileDtoMapper performerProfileDtoMapper;
    private final PersonRepository personRepository;

    public PerformerProfileService(PerformerProfileRepository performerProfileRepository, PerformerProfileDtoMapper performerProfileDtoMapper, PersonRepository personRepository) {
        this.performerProfileRepository = performerProfileRepository;
        this.performerProfileDtoMapper = performerProfileDtoMapper;
        this.personRepository = personRepository;

    }

    @Transactional(readOnly = true)
    public List<PerformerProfileResponseDto> getAllPerformerProfiles() {
        return performerProfileDtoMapper.mapToDto(performerProfileRepository.findAll());
    }

    @Transactional(readOnly = true)
    public PerformerProfileResponseDto getPerformerProfileById(long id) {
        PerformerProfileEntity entity = performerProfileRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("PerformerProfile with id " + id + " not found."));

        return performerProfileDtoMapper.mapToDto(entity);
    }

    public PerformerProfileResponseDto createPerformerProfile(PerformerProfileRequestDto performerProfileRequestDto) {
        // Create the entity the repository expects
        PerformerProfileEntity performerProfileEntity = performerProfileDtoMapper.mapToEntity(performerProfileRequestDto);
        // Extract personId
        Long personId = performerProfileRequestDto.getPersonId();
        // Find PersonEntity
        PersonEntity person = personRepository.findById(personId)
                .orElseThrow(() -> new RecordNotFoundException("Person with id " + personId + " not found."));
        // Set the related person
        performerProfileEntity.setPersonEntity(person);
        // Save the entity in the repository
        performerProfileEntity = performerProfileRepository.save(performerProfileEntity);
        // Convert the saved entity to a response DTO
        return performerProfileDtoMapper.mapToDto(performerProfileEntity);
    }

    public PerformerProfileResponseDto updatePerformerProfile(Long id, PerformerProfileRequestDto performerProfileRequestDto) {
        // Retrieve the entity from the database with its current values
        PerformerProfileEntity existingPerformerProfileEntity = getPerformerProfileEntity(id);

        // Get PersonId from requestDto
        Long personId = performerProfileRequestDto.getPersonId();

        // Get PersonEntity from repository
        PersonEntity person = personRepository.findById(personId)
                .orElseThrow(() ->
                        new RecordNotFoundException("Person with id " + personId + " not found."));

        // Update Person field
        existingPerformerProfileEntity.setPersonEntity(person);

        // Save update to repository
        existingPerformerProfileEntity = performerProfileRepository.save(existingPerformerProfileEntity);

        // Convert the updated entity to a response DTO
        return performerProfileDtoMapper.mapToDto(existingPerformerProfileEntity);
    }

    public void deletePerformerProfile(Long id) {
        PerformerProfileEntity performerProfile = getPerformerProfileEntity(id);
        performerProfileRepository.delete(performerProfile);
    }

    // Helper: gets entity from repository
    private PerformerProfileEntity getPerformerProfileEntity(Long id) {
        PerformerProfileEntity performerProfileEntity = performerProfileRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("PerformerProfile with id " + id + " not found."));
        return performerProfileEntity;
    }
}
