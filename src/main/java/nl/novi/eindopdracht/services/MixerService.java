package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.dtos.mixer.MixerRequestDto;
import nl.novi.eindopdracht.dtos.mixer.MixerResponseDto;
import nl.novi.eindopdracht.entities.MixerEntity;
import nl.novi.eindopdracht.exceptions.RecordNotFoundException;
import nl.novi.eindopdracht.mappers.MixerDtoMapper;
import nl.novi.eindopdracht.repositories.MixerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MixerService {

    private final MixerRepository mixerRepository;
    private final MixerDtoMapper mixerDtoMapper;


    public MixerService(MixerRepository mixerRepository, MixerDtoMapper mixerDtoMapper) {
        this.mixerRepository = mixerRepository;
        this.mixerDtoMapper = mixerDtoMapper;
    }

    @Transactional(readOnly = true)
    public List<MixerResponseDto> getAllMixers() {
        return mixerDtoMapper.mapToDto(mixerRepository.findAll());
    }

    @Transactional(readOnly = true)
    public MixerResponseDto getMixerById(long id) {
        MixerEntity entity = mixerRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Mixer with id " + id + " not found."));

        return mixerDtoMapper.mapToDto(entity);
    }

    public MixerResponseDto createMixer(MixerRequestDto mixerRequestDto) {
        // Create the entity the repository expects
        MixerEntity mixerEntity = mixerDtoMapper.mapToEntity(mixerRequestDto);
        // Save the entity in the repository
        mixerEntity = mixerRepository.save(mixerEntity);
        // Convert the saved entity to a response DTO
        return mixerDtoMapper.mapToDto(mixerEntity);
    }

    public MixerResponseDto updateMixer(Long id, MixerRequestDto mixerRequestDto) {
        // Retrieve the entity from the database with its current values
        MixerEntity existingMixerEntity = getMixerEntity(id);

        // Change field
        existingMixerEntity.setBrand(mixerRequestDto.getBrand());
        existingMixerEntity.setModel(mixerRequestDto.getModel());

        // Save update to repository
        existingMixerEntity = mixerRepository.save(existingMixerEntity);

        // Convert the updated entity to a response DTO
        return mixerDtoMapper.mapToDto(existingMixerEntity);
    }

    public void deleteMixer(Long id) {
        MixerEntity mixer = getMixerEntity(id);
        // If there are any relations, remove these first by setting the fields to null.
        mixerRepository.delete(mixer);
    }

    // Helper: gets entity from repository
    private MixerEntity getMixerEntity(Long id) {
        MixerEntity mixerEntity = mixerRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Mixer " + id + " not found"));
        return mixerEntity;
    }
}
