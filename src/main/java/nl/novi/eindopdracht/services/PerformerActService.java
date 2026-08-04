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
        return performerActDtoMapper.mapToDto(getPerformerActEntity(id));
    }

    public PerformerActResponseDto createPerformerAct(PerformerActRequestDto performerActRequestDto) {
        // Create the entity the repository expects
        PerformerActEntity performerActEntity = performerActDtoMapper.mapToEntity(performerActRequestDto);

        // PerformerProfile
        // Extract PerformerId
        Long performerId = performerActRequestDto.getPerformerId();

        // Find PerformerEntity
        PerformerProfileEntity performer = getPerformerEntity(performerId);

        // Set related PerformerProfile
        performerActEntity.setPerformerEntity(performer);

        // Act
        // Extract ActId
        Long actId = performerActRequestDto.getActId();

        // Find ActEntity
        ActEntity act = getActEntity(actId);

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
        PerformerProfileEntity performer = getPerformerEntity(performerId);

        // Update field
        existingPerformerActEntity.setPerformerEntity(performer);

        // Act
        // Extract ActId
        Long actId = performerActRequestDto.getActId();

        // Find ActEntity
        ActEntity act = getActEntity(actId);

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

    // Helpers
    private PerformerActEntity getPerformerActEntity(Long id) {
        return performerActRepository.findById(id)
                .orElseThrow(() ->
                        new RecordNotFoundException("PerformerAct with id " + id + " not found."));
    }

    private ActEntity getActEntity(Long id) {
        return actRepository.findById(id)
                .orElseThrow(() ->
                        new RecordNotFoundException("Act with id " + id + " not found."));
    }

    private PerformerProfileEntity getPerformerEntity(Long id) {
        return performerProfileRepository.findById(id)
                .orElseThrow(() ->
                        new RecordNotFoundException("Performer with id " + id + " not found."));
    }
}
