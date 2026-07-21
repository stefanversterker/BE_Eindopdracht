package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.dtos.microphone.MicrophoneRequestDto;
import nl.novi.eindopdracht.dtos.microphone.MicrophoneResponseDto;
import nl.novi.eindopdracht.entities.MicrophoneEntity;
import nl.novi.eindopdracht.exceptions.RecordNotFoundException;
import nl.novi.eindopdracht.mappers.MicrophoneDtoMapper;
import nl.novi.eindopdracht.repositories.MicrophoneRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MicrophoneService {

    private final MicrophoneRepository microphoneRepository;
    private final MicrophoneDtoMapper microphoneDtoMapper;


    public MicrophoneService(MicrophoneRepository microphoneRepository, MicrophoneDtoMapper microphoneDtoMapper) {
        this.microphoneRepository = microphoneRepository;
        this.microphoneDtoMapper = microphoneDtoMapper;
    }

    @Transactional(readOnly = true)
    public List<MicrophoneResponseDto> getAllMicrophones() {
        return microphoneDtoMapper.mapToDto(microphoneRepository.findAll());
    }

    @Transactional(readOnly = true)
    public MicrophoneResponseDto getMicrophoneById(long id) {
        MicrophoneEntity entity = microphoneRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Microphone with id " + id + " not found."));

        return microphoneDtoMapper.mapToDto(entity);
    }

    public MicrophoneResponseDto createMicrophone(MicrophoneRequestDto microphoneRequestDto) {
        // Create the entity the repository expects
        MicrophoneEntity microphoneEntity = microphoneDtoMapper.mapToEntity(microphoneRequestDto);
        // Save the entity in the repository
        microphoneEntity = microphoneRepository.save(microphoneEntity);
        // Convert the saved entity to a response DTO
        return microphoneDtoMapper.mapToDto(microphoneEntity);
    }

    public MicrophoneResponseDto updateMicrophone(Long id, MicrophoneRequestDto microphoneRequestDto) {
        // Retrieve the entity from the database with its current values
        MicrophoneEntity existingMicrophoneEntity = getMicrophoneEntity(id);

        // Change field
        existingMicrophoneEntity.setBrand(microphoneRequestDto.getBrand());
        existingMicrophoneEntity.setModel(microphoneRequestDto.getModel());
        existingMicrophoneEntity.setPolarPatterns(microphoneRequestDto.getPolarPatterns());
        existingMicrophoneEntity.setPhantomRequired(microphoneRequestDto.isPhantomRequired());


        // Save update to repository
        existingMicrophoneEntity = microphoneRepository.save(existingMicrophoneEntity);

        // Convert the updated entity to a response DTO
        return microphoneDtoMapper.mapToDto(existingMicrophoneEntity);
    }

    public void deleteMicrophone(Long id) {
        MicrophoneEntity microphone = getMicrophoneEntity(id);
        // If there are any relations, remove these first by setting the fields to null.
        microphoneRepository.delete(microphone);
    }

    // Helper: gets entity from repository
    private MicrophoneEntity getMicrophoneEntity(Long id) {
        MicrophoneEntity microphoneEntity = microphoneRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Microphone " + id + " not found"));
        return microphoneEntity;
    }
}
