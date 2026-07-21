package nl.novi.eindopdracht.services;


import nl.novi.eindopdracht.dtos.instrument.InstrumentRequestDto;
import nl.novi.eindopdracht.dtos.instrument.InstrumentResponseDto;
import nl.novi.eindopdracht.entities.InstrumentEntity;
import nl.novi.eindopdracht.exceptions.RecordNotFoundException;
import nl.novi.eindopdracht.mappers.InstrumentDtoMapper;
import nl.novi.eindopdracht.repositories.InstrumentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Service
public class InstrumentService {

    private final InstrumentRepository instrumentRepository;
    private final InstrumentDtoMapper instrumentDtoMapper;


    public InstrumentService(InstrumentRepository instrumentRepository, InstrumentDtoMapper instrumentDtoMapper) {
        this.instrumentRepository = instrumentRepository;
        this.instrumentDtoMapper = instrumentDtoMapper;
    }

    @Transactional(readOnly = true)
    public List<InstrumentResponseDto> getAllInstruments() {
        return instrumentDtoMapper.mapToDto(instrumentRepository.findAll());
    }

    @Transactional(readOnly = true)
    public InstrumentResponseDto getInstrumentById(long id) {
        InstrumentEntity entity = instrumentRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Instrument with id " + id + " not found."));

        return instrumentDtoMapper.mapToDto(entity);
    }

    public InstrumentResponseDto createInstrument(InstrumentRequestDto instrumentRequestDto) {
        // Create the entity the repository expects
        InstrumentEntity instrumentEntity = instrumentDtoMapper.mapToEntity(instrumentRequestDto);
        // Save the entity in the repository
        instrumentEntity = instrumentRepository.save(instrumentEntity);
        // Convert the saved entity to a response DTO
        return instrumentDtoMapper.mapToDto(instrumentEntity);
    }

    public InstrumentResponseDto updateInstrument(Long id, InstrumentRequestDto instrumentRequestDto) {
        // Retrieve the entity from the database with its current values
        InstrumentEntity existingInstrumentEntity = getInstrumentEntity(id);

        // Change field
        existingInstrumentEntity.setName(instrumentRequestDto.getName());

        // Save update to repository
        existingInstrumentEntity = instrumentRepository.save(existingInstrumentEntity);

        // Convert the updated entity to a response DTO
        return instrumentDtoMapper.mapToDto(existingInstrumentEntity);
    }

    public void deleteInstrument(Long id) {
        InstrumentEntity instrument = getInstrumentEntity(id);
        // If there are any relations, remove these first by setting the fields to null.
        instrumentRepository.delete(instrument);
    }

    // Helper: gets entity from repository
    private InstrumentEntity getInstrumentEntity(Long id) {
        InstrumentEntity instrumentEntity = instrumentRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Instrument " + id + " not found"));
        return instrumentEntity;
    }
}
