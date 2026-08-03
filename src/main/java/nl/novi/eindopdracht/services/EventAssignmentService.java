package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.dtos.eventAssignment.EventAssignmentRequestDto;
import nl.novi.eindopdracht.dtos.eventAssignment.EventAssignmentResponseDto;
import nl.novi.eindopdracht.entities.PersonEntity;
import nl.novi.eindopdracht.entities.EventEntity;
import nl.novi.eindopdracht.entities.EventAssignmentEntity;
import nl.novi.eindopdracht.exceptions.RecordNotFoundException;
import nl.novi.eindopdracht.mappers.EventAssignmentDtoMapper;
import nl.novi.eindopdracht.repositories.PersonRepository;
import nl.novi.eindopdracht.repositories.EventRepository;
import nl.novi.eindopdracht.repositories.EventAssignmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventAssignmentService {

    private final EventAssignmentRepository eventAssignmentRepository;
    private final EventAssignmentDtoMapper eventAssignmentDtoMapper;
    private final PersonRepository personRepository;
    private final EventRepository eventRepository;


    public EventAssignmentService(
            EventAssignmentRepository eventAssignmentRepository,
            EventAssignmentDtoMapper eventAssignmentDtoMapper,
            PersonRepository personRepository,
            EventRepository eventRepository
    ) {
        this.eventAssignmentRepository = eventAssignmentRepository;
        this.eventAssignmentDtoMapper = eventAssignmentDtoMapper;
        this.personRepository = personRepository;
        this.eventRepository = eventRepository;
    }

    @Transactional(readOnly = true)
    public List<EventAssignmentResponseDto> getAllEventAssignments() {
        return eventAssignmentDtoMapper.mapToDto(eventAssignmentRepository.findAll());
    }

    @Transactional(readOnly = true)
    public EventAssignmentResponseDto getEventAssignmentById(long id) {
        return eventAssignmentDtoMapper.mapToDto(getEventAssignmentEntity(id));
    }

    public EventAssignmentResponseDto createEventAssignment(EventAssignmentRequestDto eventAssignmentRequestDto) {
        // Create the entity the repository expects
        EventAssignmentEntity eventAssignmentEntity = eventAssignmentDtoMapper.mapToEntity(eventAssignmentRequestDto);

        // Person
        // Extract PersonId
        Long personId = eventAssignmentRequestDto.getPersonId();

        // Find PersonEntity
        PersonEntity person = getPersonEntity(personId);

        // Set related Person
        eventAssignmentEntity.setPerson(person);


        // Event
        // Extract EventId
        Long eventId = eventAssignmentRequestDto.getEventId();

        // Find EventEntity
        EventEntity event = getEventEntity(eventId);

        // Set related Event
        eventAssignmentEntity.setEvent(event);

        // Save the entity in the repository
        eventAssignmentEntity = eventAssignmentRepository.save(eventAssignmentEntity);

        // Convert the saved entity to a response DTO
        return eventAssignmentDtoMapper.mapToDto(eventAssignmentEntity);
    }

    public EventAssignmentResponseDto updateEventAssignment(Long id, EventAssignmentRequestDto eventAssignmentRequestDto) {
        // Retrieve the entity from the database with its current values
        EventAssignmentEntity existingEventAssignmentEntity = getEventAssignmentEntity(id);

        // Person
        // Extract PersonId
        Long personId = eventAssignmentRequestDto.getPersonId();

        // Find PersonEntity
        PersonEntity person = getPersonEntity(personId);

        // Update field
        existingEventAssignmentEntity.setPerson(person);

        // Event
        // Extract EventId
        Long eventId = eventAssignmentRequestDto.getEventId();

        // Find EventEntity
        EventEntity event = getEventEntity(eventId);

        // Update field
        existingEventAssignmentEntity.setEvent(event);

        // EventRole
        // Update field
        existingEventAssignmentEntity.setEventRole(eventAssignmentRequestDto.getEventRole());

        // Save update to repository
        existingEventAssignmentEntity = eventAssignmentRepository.save(existingEventAssignmentEntity);

        // Convert the updated entity to a response DTO
        return eventAssignmentDtoMapper.mapToDto(existingEventAssignmentEntity);
    }

    public void deleteEventAssignment(Long id) {
        EventAssignmentEntity eventAssignment = getEventAssignmentEntity(id);
        eventAssignmentRepository.delete(eventAssignment);
    }

    // Helpers
    private EventAssignmentEntity getEventAssignmentEntity(Long id) {
        return eventAssignmentRepository.findById(id)
                .orElseThrow(() ->
                        new RecordNotFoundException("EventAssignment with id " + id + " not found."));
    }

    private PersonEntity getPersonEntity(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() ->
                        new RecordNotFoundException("Person with id " + id + " not found."));
    }

    private EventEntity getEventEntity(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() ->
                        new RecordNotFoundException("Event with id " + id + " not found."));
    }
    
}
