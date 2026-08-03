package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.dtos.source.SourceRequestDto;
import nl.novi.eindopdracht.dtos.source.SourceResponseDto;
import nl.novi.eindopdracht.entities.PerformerInstrumentEntity;
import nl.novi.eindopdracht.entities.SourceEntity;
import nl.novi.eindopdracht.exceptions.RecordNotFoundException;
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

    public SourceService(
            SourceRepository sourceRepository,
            SourceDtoMapper sourceDtoMapper,
            PerformerInstrumentRepository performerInstrumentRepository) {
        this.sourceRepository = sourceRepository;
        this.sourceDtoMapper = sourceDtoMapper;
        this.performerInstrumentRepository = performerInstrumentRepository;
    }

    @Transactional(readOnly = true)
    public List<SourceResponseDto> getAllSources() {
        return sourceDtoMapper.mapToDto(sourceRepository.findAll());
    }

    @Transactional(readOnly = true)
    public SourceResponseDto getSourceById(long id) {
        return sourceDtoMapper.mapToDto(getSourceEntity(id));
    }

    public SourceResponseDto createSource(SourceRequestDto sourceRequestDto) {
        // Create the entity the repository expects
        SourceEntity sourceEntity = sourceDtoMapper.mapToEntity(sourceRequestDto);

        // Extract PerformerInstrumentId
        Long performerInstrumentId = sourceRequestDto.getPerformerInstrumentId();

        // Find PerformerInstrumentEntity
        PerformerInstrumentEntity performerInstrument = getPerformerInstrumentEntity(performerInstrumentId);

        // Set related PerformerInstrument
        sourceEntity.setPerformerInstrumentEntity(performerInstrument);

        // Save the entity in the repository
        sourceEntity = sourceRepository.save(sourceEntity);

        // Convert the saved entity to a response DTO
        return sourceDtoMapper.mapToDto(sourceEntity);
    }

    public SourceResponseDto updateSource(Long id, SourceRequestDto sourceRequestDto) {
        // Retrieve the entity from the database with its current values
        SourceEntity existingSourceEntity = getSourceEntity(id);

        // Update Name field
        existingSourceEntity.setName(sourceRequestDto.getName());

        // Extract PerformerInstrumentId
        Long performerInstrumentId = sourceRequestDto.getPerformerInstrumentId();

        // Find PerformerInstrumentEntity
        PerformerInstrumentEntity performerInstrument = getPerformerInstrumentEntity(performerInstrumentId);

        // Update PerformerInstrument field
        existingSourceEntity.setPerformerInstrumentEntity(performerInstrument);

        // Save update to repository
        existingSourceEntity = sourceRepository.save(existingSourceEntity);

        // Convert the updated entity to a response DTO
        return sourceDtoMapper.mapToDto(existingSourceEntity);
    }

    public void deleteSource(Long id) {
        SourceEntity source = getSourceEntity(id);
        sourceRepository.delete(source);
    }

    // Helpers
    private SourceEntity getSourceEntity(Long id) {
        return sourceRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Source with id " + id + " not found."));
    }

    private PerformerInstrumentEntity getPerformerInstrumentEntity(Long id) {
        return performerInstrumentRepository.findById(id)
                .orElseThrow(() ->
                        new RecordNotFoundException("PerformerInstrument with id " + id + " not found."));
    }
}
