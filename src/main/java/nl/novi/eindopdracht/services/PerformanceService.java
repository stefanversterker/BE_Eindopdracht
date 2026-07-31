package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.dtos.performance.PerformanceRequestDto;
import nl.novi.eindopdracht.dtos.performance.PerformanceResponseDto;
import nl.novi.eindopdracht.entities.ActEntity;
import nl.novi.eindopdracht.entities.EventEntity;
import nl.novi.eindopdracht.entities.PerformanceEntity;
import nl.novi.eindopdracht.exceptions.RecordNotFoundException;
import nl.novi.eindopdracht.mappers.PerformanceDtoMapper;
import nl.novi.eindopdracht.repositories.ActRepository;
import nl.novi.eindopdracht.repositories.EventRepository;
import nl.novi.eindopdracht.repositories.PerformanceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PerformanceService {

    private final PerformanceRepository performanceRepository;
    private final PerformanceDtoMapper performanceDtoMapper;
    private final ActRepository actRepository;
    private final EventRepository eventRepository;


    public PerformanceService(
            PerformanceRepository performanceRepository,
            PerformanceDtoMapper performanceDtoMapper,
            ActRepository actRepository,
            EventRepository eventRepository
    ) {
        this.performanceRepository = performanceRepository;
        this.performanceDtoMapper = performanceDtoMapper;
        this.actRepository = actRepository;
        this.eventRepository = eventRepository;
    }

    @Transactional(readOnly = true)
    public List<PerformanceResponseDto> getAllPerformances() {
        return performanceDtoMapper.mapToDto(performanceRepository.findAll());
    }

    @Transactional(readOnly = true)
    public PerformanceResponseDto getPerformanceById(long id) {
        PerformanceEntity entity = performanceRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Performance with id " + id + " not found."));

        return performanceDtoMapper.mapToDto(entity);
    }

    public PerformanceResponseDto createPerformance(PerformanceRequestDto performanceRequestDto) {
        // Create the entity the repository expects
        PerformanceEntity performanceEntity = performanceDtoMapper.mapToEntity(performanceRequestDto);

        // Extract ActProfileId
        Long actId = performanceRequestDto.getActId();

        // Find ActEntity
        ActEntity act = actRepository.findById(actId)
                .orElseThrow(() -> new RecordNotFoundException("Act with id " + actId + " not found."));

        // Set related Act
        performanceEntity.setAct(act);


        // Extract EventProfileId
        Long eventId = performanceRequestDto.getEventId();

        // Find EventEntity
        EventEntity event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RecordNotFoundException("Event with id " + eventId + " not found."));

        // Set related Event
        performanceEntity.setEvent(event);

        // Save the entity in the repository
        performanceEntity = performanceRepository.save(performanceEntity);

        // Convert the saved entity to a response DTO
        return performanceDtoMapper.mapToDto(performanceEntity);
    }

    public PerformanceResponseDto updatePerformance(Long id, PerformanceRequestDto performanceRequestDto) {
        // Retrieve the entity from the database with its current values
        PerformanceEntity existingPerformanceEntity = getPerformanceEntity(id);

        // Act
        // Extract ActId
        Long actId = performanceRequestDto.getActId();

        // Find ActEntity
        ActEntity act = actRepository.findById(actId)
                .orElseThrow(() -> new RecordNotFoundException("Act with id " + actId + " not found."));

        // Update fields
        existingPerformanceEntity.setAct(act);

        // Event
        // Extract EventId
        Long eventId = performanceRequestDto.getEventId();

        // Find EventEntity
        EventEntity event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RecordNotFoundException("Event with id " + eventId + " not found."));


        // Update fields
        existingPerformanceEntity.setEvent(event);

        // Save update to repository
        existingPerformanceEntity = performanceRepository.save(existingPerformanceEntity);

        // Convert the updated entity to a response DTO
        return performanceDtoMapper.mapToDto(existingPerformanceEntity);
    }

    public void deletePerformance(Long id) {
        PerformanceEntity performance = getPerformanceEntity(id);
        performanceRepository.delete(performance);
    }

    // Helper: gets entity from repository
    private PerformanceEntity getPerformanceEntity(Long id) {
        PerformanceEntity performanceEntity = performanceRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Performance " + id + " not found"));
        return performanceEntity;
    }
}
