package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.dtos.act.ActRequestDto;
import nl.novi.eindopdracht.dtos.act.ActResponseDto;
import nl.novi.eindopdracht.entities.ActEntity;
import nl.novi.eindopdracht.exceptions.RecordNotFoundException;
import nl.novi.eindopdracht.mappers.ActDtoMapper;
import nl.novi.eindopdracht.repositories.ActRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ActService {

    private final ActRepository actRepository;
    private final ActDtoMapper actDtoMapper;


    public ActService(ActRepository actRepository, ActDtoMapper actDtoMapper) {
        this.actRepository = actRepository;
        this.actDtoMapper = actDtoMapper;
    }

    @Transactional(readOnly = true)
    public List<ActResponseDto> getAllActs() {
        return actDtoMapper.mapToDto(actRepository.findAll());
    }

    @Transactional(readOnly = true)
    public ActResponseDto getActById(long id) {
        ActEntity entity = actRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Act with id " + id + "not found."));

        return actDtoMapper.mapToDto(entity);
    }

    public ActResponseDto createAct(ActRequestDto actRequestDto) {
        // Create the entity the repository expects
        ActEntity actEntity = actDtoMapper.mapToEntity(actRequestDto);
        // Save the entity in the repository
        actEntity = actRepository.save(actEntity);
        // Convert the saved entity to a response DTO
        return actDtoMapper.mapToDto(actEntity);
    }

    public ActResponseDto updateAct(Long id, ActRequestDto actRequestDto) {
        // Retrieve the entity from the database with its current values
        ActEntity existingActEntity = getActEntity(id);

        // Change field
        existingActEntity.setName(actRequestDto.getName());
        existingActEntity.setPhone(actRequestDto.getPhone());
        existingActEntity.setEmail(actRequestDto.getEmail());

        // Save update to repository
        existingActEntity = actRepository.save(existingActEntity);

        // Convert the updated entity to a response DTO
        return actDtoMapper.mapToDto(existingActEntity);
    }

    public void deleteAct(Long id) {
        ActEntity act = getActEntity(id);
        // If there are any relations, remove these first by setting the fields to null.
        actRepository.delete(act);
    }

    // Helper: gets entity from repository
    private ActEntity getActEntity(Long id) {
        ActEntity actEntity = actRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Act " + id + " not found"));
        return actEntity;
    }

}
