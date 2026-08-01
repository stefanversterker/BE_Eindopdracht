package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.dtos.performerAct.PerformerActRequestDto;
import nl.novi.eindopdracht.dtos.performerAct.PerformerActResponseDto;
import nl.novi.eindopdracht.entities.*;
import nl.novi.eindopdracht.exceptions.RecordNotFoundException;
import nl.novi.eindopdracht.mappers.PerformerActDtoMapper;
import nl.novi.eindopdracht.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PerformerActService {

    private final PerformerActRepository performerActRepository;
    private final PerformerActDtoMapper performerActDtoMapper;
    private final PerformerProfileRepository performerProfileRepository;
    private final ActRepository actRepository;


    public PerformerActService(
            PerformerActRepository performerActRepository,
            PerformerActDtoMapper performerActDtoMapper,
            PerformerProfileRepository performerProfileRepository,
            ActRepository actRepository
    ) {
        this.performerActRepository = performerActRepository;
        this.performerActDtoMapper = performerActDtoMapper;
        this.performerProfileRepository = performerProfileRepository;
        this.actRepository = actRepository;
    }

    @Transactional(readOnly = true)
    public List<PerformerActResponseDto> getAllPerformerActs() {
        return performerActDtoMapper.mapToDto(performerActRepository.findAll());
    }

    @Transactional(readOnly = true)
    public PerformerActResponseDto getPerformerActById(long id) {
        PerformerActEntity entity = performerActRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("PerformerAct with id " + id + " not found."));

        return performerActDtoMapper.mapToDto(entity);
    }

    public PerformerActResponseDto createPerformerAct(PerformerActRequestDto performerActRequestDto) {
        // Create the entity the repository expects
        PerformerActEntity performerActEntity = performerActDtoMapper.mapToEntity(performerActRequestDto);

        // PerformerProfile
        // Extract PerformerId
        Long performerId = performerActRequestDto.getPerformerId();

        // Find PerformerEntity
        PerformerProfileEntity performer = performerProfileRepository.findById(performerId)
                .orElseThrow(() -> new RecordNotFoundException("Performer with id " + performerId + " not found."));

        // Set related PerformerProfile
        performerActEntity.setPerformerEntity(performer);

        // Act
        // Extract ActId
        Long actId = performerActRequestDto.getActId();

        // Find ActEntity
        ActEntity act = actRepository.findById(actId)
                .orElseThrow(() -> new RecordNotFoundException("Act with id " + actId + " not found."));

        // Set related Event
        performerActEntity.setActEntity(act);

        // Save the entity in the repository
        performerActEntity = performerActRepository.save(performerActEntity);

        // Convert the saved entity to a response DTO
        return performerActDtoMapper.mapToDto(performerActEntity);
    }

    public PerformerActResponseDto updatePerformerAct(Long id, PerformerActRequestDto performerActRequestDto) {
        // Retrieve the entity from the database with its current values
        PerformerActEntity existingPerformerActEntity = getPerformerActEntity(id);

        // Performer
        // Extract PerformerId
        Long performerId = performerActRequestDto.getPerformerId();

        // Find PerformerEntity
        PerformerProfileEntity performer = performerProfileRepository.findById(performerId)
                .orElseThrow(() -> new RecordNotFoundException("Performer with id " + performerId + " not found."));

        // Update field
        existingPerformerActEntity.setPerformerEntity(performer);

        // Act
        // Extract ActId
        Long actId = performerActRequestDto.getActId();

        // Find ActEntity
        ActEntity act = actRepository.findById(actId)
                .orElseThrow(() -> new RecordNotFoundException("Act with id " + actId + " not found."));

        // Update field
        existingPerformerActEntity.setActEntity(act);

        // Roles
        // Update field
        existingPerformerActEntity.setRoles(performerActRequestDto.getRoles());

        // Save update to repository
        existingPerformerActEntity = performerActRepository.save(existingPerformerActEntity);

        // Convert the updated entity to a response DTO
        return performerActDtoMapper.mapToDto(existingPerformerActEntity);
    }

    public void deletePerformerAct(Long id) {
        PerformerActEntity performerAct = getPerformerActEntity(id);
        performerActRepository.delete(performerAct);
    }

    // Helper: gets entity from repository
    private PerformerActEntity getPerformerActEntity(Long id) {
        PerformerActEntity performerActEntity = performerActRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("PerformerAct with id " + id + " not found."));
        return performerActEntity;
    }
}
