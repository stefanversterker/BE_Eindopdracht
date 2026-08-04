package nl.novi.eindopdracht.services;


import nl.novi.eindopdracht.dtos.performerInstrument.PerformerInstrumentRequestDto;
import nl.novi.eindopdracht.dtos.performerInstrument.PerformerInstrumentResponseDto;
import nl.novi.eindopdracht.entities.InstrumentEntity;
import nl.novi.eindopdracht.entities.PerformerInstrumentEntity;
import nl.novi.eindopdracht.entities.PerformerProfileEntity;
import nl.novi.eindopdracht.exceptions.RecordInUseException;
import nl.novi.eindopdracht.exceptions.RecordNotFoundException;
import nl.novi.eindopdracht.mappers.PerformerInstrumentDtoMapper;
import nl.novi.eindopdracht.repositories.InstrumentRepository;
import nl.novi.eindopdracht.repositories.PerformerInstrumentRepository;
import nl.novi.eindopdracht.repositories.PerformerProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PerformerInstrumentService {

    private final PerformerInstrumentRepository performerInstrumentRepository;
    private final PerformerInstrumentDtoMapper performerInstrumentDtoMapper;
    private final PerformerProfileRepository performerProfileRepository;
    private final InstrumentRepository instrumentRepository;


    public PerformerInstrumentService(
            PerformerInstrumentRepository performerInstrumentRepository,
            PerformerInstrumentDtoMapper performerInstrumentDtoMapper,
            PerformerProfileRepository performerProfileRepository,
            InstrumentRepository instrumentRepository
    ) {
        this.performerInstrumentRepository = performerInstrumentRepository;
        this.performerInstrumentDtoMapper = performerInstrumentDtoMapper;
        this.performerProfileRepository = performerProfileRepository;
        this.instrumentRepository = instrumentRepository;
    }

    @Transactional(readOnly = true)
    public List<PerformerInstrumentResponseDto> getAllPerformerInstruments() {
        return performerInstrumentDtoMapper.mapToDto(performerInstrumentRepository.findAll());
    }

    @Transactional(readOnly = true)
    public PerformerInstrumentResponseDto getPerformerInstrumentById(long id) {
        return performerInstrumentDtoMapper.mapToDto(getPerformerInstrumentEntity(id));
    }

    public PerformerInstrumentResponseDto createPerformerInstrument(PerformerInstrumentRequestDto performerInstrumentRequestDto) {
        // Create the entity the repository expects
        PerformerInstrumentEntity performerInstrumentEntity = performerInstrumentDtoMapper.mapToEntity(performerInstrumentRequestDto);

        // Extract PerformerProfileId
        Long performerProfileId = performerInstrumentRequestDto.getPerformerProfileId();

        // Find PerformerProfileEntity
        PerformerProfileEntity performer = getPerformerProfileEntity(performerProfileId);

        // Set related performerProfile
        performerInstrumentEntity.setPerformerProfileEntity(performer);

        // Extract InstrumentId
        Long instrumentId = performerInstrumentRequestDto.getInstrumentId();

        // Find InstrumentEntity
        InstrumentEntity instrument = getInstrumentEntity(instrumentId);

        // Set Related instrument
        performerInstrumentEntity.setInstrumentEntity(instrument);

        // Save the entity in the repository
        performerInstrumentEntity = performerInstrumentRepository.save(performerInstrumentEntity);

        // Convert the saved entity to a response DTO
        return performerInstrumentDtoMapper.mapToDto(performerInstrumentEntity);
    }

    public PerformerInstrumentResponseDto updatePerformerInstrument(Long id, PerformerInstrumentRequestDto performerInstrumentRequestDto) {
        // Retrieve the entity from the database with its current values
        PerformerInstrumentEntity existingPerformerInstrumentEntity = getPerformerInstrumentEntity(id);

        //PerformerProfile
        // Extract PerformerProfileId
        Long performerProfileId = performerInstrumentRequestDto.getPerformerProfileId();

        // Find PerformerProfileEntity
        PerformerProfileEntity performer = getPerformerProfileEntity(performerProfileId);

        // Update field
        existingPerformerInstrumentEntity.setPerformerProfileEntity(performer);

        //Instrument
        // Extract InstrumentId
        Long instrumentId = performerInstrumentRequestDto.getInstrumentId();

        // Find InstrumentEntity
        InstrumentEntity instrument = getInstrumentEntity(instrumentId);

        // Update field
        existingPerformerInstrumentEntity.setInstrumentEntity(instrument);


        // Save update to repository
        existingPerformerInstrumentEntity = performerInstrumentRepository.save(existingPerformerInstrumentEntity);

        // Convert the updated entity to a response DTO
        return performerInstrumentDtoMapper.mapToDto(existingPerformerInstrumentEntity);
    }

    @Transactional
    public void deletePerformerInstrument(Long id) {
        PerformerInstrumentEntity performerInstrument = getPerformerInstrumentEntity(id);
        if (!performerInstrument.getSources().isEmpty()) {
            throw new RecordInUseException(
                    "Cannot delete PerformerInstrument with id " + id +
                            " because it still has associated sources.");
        }
        performerInstrumentRepository.delete(performerInstrument);
    }

    // Helpers
    private PerformerInstrumentEntity getPerformerInstrumentEntity(Long id) {
        return performerInstrumentRepository.findById(id)
                .orElseThrow(() ->
                        new RecordNotFoundException("PerformerInstrument with id " + id + " not found."));
    }

    private PerformerProfileEntity getPerformerProfileEntity(Long id) {
        return performerProfileRepository.findById(id)
                .orElseThrow(() ->
                        new RecordNotFoundException("PerformerProfile with id " + id + " not found."));
    }

    private InstrumentEntity getInstrumentEntity(Long id) {
        return instrumentRepository.findById(id)
                .orElseThrow(() ->
                        new RecordNotFoundException("Instrument with id " + id + " not found."));
    }
}
