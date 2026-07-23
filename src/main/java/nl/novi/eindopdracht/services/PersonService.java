package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.dtos.person.PersonRequestDto;
import nl.novi.eindopdracht.dtos.person.PersonResponseDto;
import nl.novi.eindopdracht.entities.PersonEntity;
import nl.novi.eindopdracht.exceptions.RecordNotFoundException;
import nl.novi.eindopdracht.mappers.PersonDtoMapper;
import nl.novi.eindopdracht.repositories.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonDtoMapper personDtoMapper;


    public PersonService(PersonRepository personRepository, PersonDtoMapper personDtoMapper) {
        this.personRepository = personRepository;
        this.personDtoMapper = personDtoMapper;
    }

    @Transactional(readOnly = true)
    public List<PersonResponseDto> getAllPersons() {
        return personDtoMapper.mapToDto(personRepository.findAll());
    }

    @Transactional(readOnly = true)
    public PersonResponseDto getPersonById(long id) {
        PersonEntity entity = personRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Person with id " + id + " not found."));

        return personDtoMapper.mapToDto(entity);
    }

    public PersonResponseDto createPerson(PersonRequestDto personRequestDto) {
        // Create the entity the repository expects
        PersonEntity personEntity = personDtoMapper.mapToEntity(personRequestDto);
        // Save the entity in the repository
        personEntity = personRepository.save(personEntity);
        // Convert the saved entity to a response DTO
        return personDtoMapper.mapToDto(personEntity);
    }

    public PersonResponseDto updatePerson(Long id, PersonRequestDto personRequestDto) {
        // Retrieve the entity from the database with its current values
        PersonEntity existingPersonEntity = getPersonEntity(id);

        // Change field
        existingPersonEntity.setFirstName(personRequestDto.getFirstName());
        existingPersonEntity.setLastName(personRequestDto.getLastName());
        existingPersonEntity.setEmail(personRequestDto.getEmail());
        existingPersonEntity.setPhone(personRequestDto.getPhone());

        // Save update to repository
        existingPersonEntity = personRepository.save(existingPersonEntity);

        // Convert the updated entity to a response DTO
        return personDtoMapper.mapToDto(existingPersonEntity);
    }

    public void deletePerson(Long id) {
        PersonEntity person = getPersonEntity(id);
        // If there are any relations, remove these first by setting the fields to null.
        personRepository.delete(person);
    }

    // Helper: gets entity from repository
    private PersonEntity getPersonEntity(Long id) {
        PersonEntity personEntity = personRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Person " + id + " not found"));
        return personEntity;
    }
}
