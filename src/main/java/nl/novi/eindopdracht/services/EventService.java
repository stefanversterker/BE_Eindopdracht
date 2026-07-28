package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.dtos.event.EventRequestDto;
import nl.novi.eindopdracht.dtos.event.EventResponseDto;
import nl.novi.eindopdracht.entities.EventEntity;
import nl.novi.eindopdracht.exceptions.RecordNotFoundException;
import nl.novi.eindopdracht.mappers.EventDtoMapper;
import nl.novi.eindopdracht.repositories.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EventDtoMapper eventDtoMapper;


    public EventService(EventRepository eventRepository, EventDtoMapper eventDtoMapper) {
        this.eventRepository = eventRepository;
        this.eventDtoMapper = eventDtoMapper;
    }

    @Transactional(readOnly = true)
    public List<EventResponseDto> getAllEvents() {
        return eventDtoMapper.mapToDto(eventRepository.findAll());
    }

    @Transactional(readOnly = true)
    public EventResponseDto getEventById(long id) {
        EventEntity entity = eventRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Event with id " + id + " not found."));

        return eventDtoMapper.mapToDto(entity);
    }

    public EventResponseDto createEvent(EventRequestDto eventRequestDto) {
        // Create the entity the repository expects
        EventEntity eventEntity = eventDtoMapper.mapToEntity(eventRequestDto);
        // Save the entity in the repository
        eventEntity = eventRepository.save(eventEntity);
        // Convert the saved entity to a response DTO
        return eventDtoMapper.mapToDto(eventEntity);
    }

    public EventResponseDto updateEvent(Long id, EventRequestDto eventRequestDto) {
        // Retrieve the entity from the database with its current values
        EventEntity existingEventEntity = getEventEntity(id);

        // Change field
        existingEventEntity.setDate(eventRequestDto.getDate());
        existingEventEntity.setVenue(eventRequestDto.getVenue());

        // Save update to repository
        existingEventEntity = eventRepository.save(existingEventEntity);

        // Convert the updated entity to a response DTO
        return eventDtoMapper.mapToDto(existingEventEntity);
    }

    public void deleteEvent(Long id) {
        EventEntity event = getEventEntity(id);
        eventRepository.delete(event);
    }

    // Helper: gets entity from repository
    private EventEntity getEventEntity(Long id) {
        EventEntity eventEntity = eventRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Event with id " + id + " not found"));
        return eventEntity;
    }
}
