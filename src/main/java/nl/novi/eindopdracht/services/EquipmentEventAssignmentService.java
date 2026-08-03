package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.dtos.equipmentEventAssignment.EquipmentEventAssignmentRequestDto;
import nl.novi.eindopdracht.dtos.equipmentEventAssignment.EquipmentEventAssignmentResponseDto;
import nl.novi.eindopdracht.entities.EquipmentEntity;
import nl.novi.eindopdracht.entities.EventEntity;
import nl.novi.eindopdracht.entities.EquipmentEventAssignmentEntity;
import nl.novi.eindopdracht.exceptions.RecordNotFoundException;
import nl.novi.eindopdracht.mappers.EquipmentEventAssignmentDtoMapper;
import nl.novi.eindopdracht.repositories.EquipmentRepository;
import nl.novi.eindopdracht.repositories.EventRepository;
import nl.novi.eindopdracht.repositories.EquipmentEventAssignmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EquipmentEventAssignmentService {

    private final EquipmentEventAssignmentRepository equipmentEventAssignmentRepository;
    private final EquipmentEventAssignmentDtoMapper equipmentEventAssignmentDtoMapper;
    private final EquipmentRepository equipmentRepository;
    private final EventRepository eventRepository;


    public EquipmentEventAssignmentService(
            EquipmentEventAssignmentRepository equipmentEventAssignmentRepository,
            EquipmentEventAssignmentDtoMapper equipmentEventAssignmentDtoMapper,
            EquipmentRepository equipmentRepository,
            EventRepository eventRepository
    ) {
        this.equipmentEventAssignmentRepository = equipmentEventAssignmentRepository;
        this.equipmentEventAssignmentDtoMapper = equipmentEventAssignmentDtoMapper;
        this.equipmentRepository = equipmentRepository;
        this.eventRepository = eventRepository;
    }

    @Transactional(readOnly = true)
    public List<EquipmentEventAssignmentResponseDto> getAllEquipmentEventAssignments() {
        return equipmentEventAssignmentDtoMapper.mapToDto(equipmentEventAssignmentRepository.findAll());
    }

    @Transactional(readOnly = true)
    public EquipmentEventAssignmentResponseDto getEquipmentEventAssignmentById(long id) {
        return equipmentEventAssignmentDtoMapper.mapToDto(getEquipmentEventAssignmentEntity(id));
    }

    public EquipmentEventAssignmentResponseDto createEquipmentEventAssignment(EquipmentEventAssignmentRequestDto equipmentEventAssignmentRequestDto) {
        // Create the entity the repository expects
        EquipmentEventAssignmentEntity equipmentEventAssignmentEntity = equipmentEventAssignmentDtoMapper.mapToEntity(equipmentEventAssignmentRequestDto);

        // Equipment
        // Extract EquipmentId
        Long equipmentId = equipmentEventAssignmentRequestDto.getEquipmentId();

        // Find EquipmentEntity
        EquipmentEntity equipment = getEquipmentEntity(equipmentId);

        // Set related Equipment
        equipmentEventAssignmentEntity.setEquipment(equipment);

        // Event
        // Extract EventId
        Long eventId = equipmentEventAssignmentRequestDto.getEventId();

        // Find EventEntity
        EventEntity event = getEventEntity(eventId);

        // Set related Event
        equipmentEventAssignmentEntity.setEvent(event);

        // Save the entity in the repository
        equipmentEventAssignmentEntity = equipmentEventAssignmentRepository.save(equipmentEventAssignmentEntity);

        // Convert the saved entity to a response DTO
        return equipmentEventAssignmentDtoMapper.mapToDto(equipmentEventAssignmentEntity);
    }

    public EquipmentEventAssignmentResponseDto updateEquipmentEventAssignment(Long id, EquipmentEventAssignmentRequestDto equipmentEventAssignmentRequestDto) {
        // Retrieve the entity from the database with its current values
        EquipmentEventAssignmentEntity existingEquipmentEventAssignmentEntity = getEquipmentEventAssignmentEntity(id);

        // Equipment
        // Extract EquipmentId
        Long equipmentId = equipmentEventAssignmentRequestDto.getEquipmentId();

        // Find EquipmentEntity
        EquipmentEntity equipment = getEquipmentEntity(equipmentId);

        // Update fields
        existingEquipmentEventAssignmentEntity.setEquipment(equipment);

        // Event
        // Extract EventId
        Long eventId = equipmentEventAssignmentRequestDto.getEventId();

        // Find EventEntity
        EventEntity event = getEventEntity(eventId);

        // Update fields
        existingEquipmentEventAssignmentEntity.setEvent(event);

        // Save update to repository
        existingEquipmentEventAssignmentEntity = equipmentEventAssignmentRepository.save(existingEquipmentEventAssignmentEntity);

        // Convert the updated entity to a response DTO
        return equipmentEventAssignmentDtoMapper.mapToDto(existingEquipmentEventAssignmentEntity);
    }

    public void deleteEquipmentEventAssignment(Long id) {
        EquipmentEventAssignmentEntity equipmentEventAssignment = getEquipmentEventAssignmentEntity(id);
        equipmentEventAssignmentRepository.delete(equipmentEventAssignment);
    }

    // Helpers
    private EquipmentEventAssignmentEntity getEquipmentEventAssignmentEntity(Long id) {
        return equipmentEventAssignmentRepository.findById(id)
                .orElseThrow(() ->
                        new RecordNotFoundException("EquipmentEventAssignment with id " + id + " not found."));
    }

    private EquipmentEntity getEquipmentEntity(Long id) {
        return equipmentRepository.findById(id)
                .orElseThrow(() ->
                        new RecordNotFoundException("Equipment with id " + id + " not found."));
    }

    private EventEntity getEventEntity(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() ->
                        new RecordNotFoundException("Event with id " + id + " not found."));
    }

}
