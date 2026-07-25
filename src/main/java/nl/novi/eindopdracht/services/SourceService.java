package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.dtos.source.SourceRequestDto;
import nl.novi.eindopdracht.dtos.source.SourceResponseDto;
import nl.novi.eindopdracht.entities.PerformerInstrumentEntity;
import nl.novi.eindopdracht.entities.PerformerProfileEntity;
import nl.novi.eindopdracht.entities.SourceEntity;
import nl.novi.eindopdracht.exceptions.RecordNotFoundException;
import nl.novi.eindopdracht.mappers.PerformerInstrumentDtoMapper;
import nl.novi.eindopdracht.mappers.SourceDtoMapper;
import nl.novi.eindopdracht.repositories.PerformerInstrumentRepository;
import nl.novi.eindopdracht.repositories.SourceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SourceService {

    private final SourceRepository sourceRepository;
    private final SourceDtoMapper sourceDtoMapper;
    private final PerformerInstrumentRepository performerInstrumentRepository;
    private final PerformerInstrumentDtoMapper performerInstrumentDtoMapper;


    public SourceService(
            SourceRepository sourceRepository,
            SourceDtoMapper sourceDtoMapper,
            PerformerInstrumentRepository performerInstrumentRepository,
            PerformerInstrumentDtoMapper performerInstrumentDtoMapper) {
        this.sourceRepository = sourceRepository;
        this.sourceDtoMapper = sourceDtoMapper;
        this.performerInstrumentRepository = performerInstrumentRepository;
        this.performerInstrumentDtoMapper = performerInstrumentDtoMapper;
    }

    @Transactional(readOnly = true)
    public List<SourceResponseDto> getAllSources() {
        return sourceDtoMapper.mapToDto(sourceRepository.findAll());
    }

    @Transactional(readOnly = true)
    public SourceResponseDto getSourceById(long id) {
        SourceEntity entity = sourceRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Source with id " + id + " not found."));

        return sourceDtoMapper.mapToDto(entity);
    }

    public SourceResponseDto createSource(SourceRequestDto sourceRequestDto) {
        // Create the entity the repository expects
        SourceEntity sourceEntity = sourceDtoMapper.mapToEntity(sourceRequestDto);
        // Save the entity in the repository
        sourceEntity = sourceRepository.save(sourceEntity);
        // Convert the saved entity to a response DTO
        return sourceDtoMapper.mapToDto(sourceEntity);
    }

    public SourceResponseDto updateSource(Long id, SourceRequestDto sourceRequestDto) {
        // Retrieve the entity from the database with its current values
        SourceEntity existingSourceEntity = getSourceEntity(id);

        // Update field
        existingSourceEntity.setName(sourceRequestDto.getName());

        // Extract PerformerInstrumentId
        Long performerInstrumentId = sourceRequestDto.getPerformerInstrumentId();

        // Find PerformerInstrumentEntity
        PerformerInstrumentEntity performerInstrument = performerInstrumentRepository.findById(performerInstrumentId)
                .orElseThrow(() -> new RecordNotFoundException("PerformerInstrument with id " + performerInstrumentId + " not found."));

        existingSourceEntity.setPerformerInstrumentEntity(performerInstrumentRepository.getPerformerInstrumentId());

        // Save update to repository
        existingSourceEntity = sourceRepository.save(existingSourceEntity);

        // Convert the updated entity to a response DTO
        return sourceDtoMapper.mapToDto(existingSourceEntity);
    }

    public void deleteSource(Long id) {
        SourceEntity source = getSourceEntity(id);
        // If there are any relations, remove these first by setting the fields to null.
        sourceRepository.delete(source);
    }

    // Helper: gets entity from repository
    private SourceEntity getSourceEntity(Long id) {
        SourceEntity sourceEntity = sourceRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Source " + id + " not found"));
        return sourceEntity;
    }

}
