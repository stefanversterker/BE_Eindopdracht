package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.dtos.channel.ChannelRequestDto;
import nl.novi.eindopdracht.dtos.channel.ChannelResponseDto;
import nl.novi.eindopdracht.entities.ChannelEntity;
import nl.novi.eindopdracht.exceptions.RecordNotFoundException;
import nl.novi.eindopdracht.mappers.ChannelDtoMapper;
import nl.novi.eindopdracht.repositories.ChannelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChannelService {

    private final ChannelRepository channelRepository;
    private final ChannelDtoMapper channelDtoMapper;


    public ChannelService(ChannelRepository channelRepository, ChannelDtoMapper channelDtoMapper) {
        this.channelRepository = channelRepository;
        this.channelDtoMapper = channelDtoMapper;
    }

    @Transactional(readOnly = true)
    public List<ChannelResponseDto> getAllChannels() {
        return channelDtoMapper.mapToDto(channelRepository.findAll());
    }

    @Transactional(readOnly = true)
    public ChannelResponseDto getChannelById(long id) {
        ChannelEntity entity = channelRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Channel with id " + id + " not found."));

        return channelDtoMapper.mapToDto(entity);
    }

    public ChannelResponseDto createChannel(ChannelRequestDto channelRequestDto) {
        // Create the entity the repository expects
        ChannelEntity channelEntity = channelDtoMapper.mapToEntity(channelRequestDto);
        // Save the entity in the repository
        channelEntity = channelRepository.save(channelEntity);
        // Convert the saved entity to a response DTO
        return channelDtoMapper.mapToDto(channelEntity);
    }

    public ChannelResponseDto updateChannel(Long id, ChannelRequestDto channelRequestDto) {
        // Retrieve the entity from the database with its current values
        ChannelEntity existingChannelEntity = getChannelEntity(id);

        // Change field
        existingChannelEntity.setNumber(channelRequestDto.getNumber());
        existingChannelEntity.setLabel(channelRequestDto.getLabel());

        // Save update to repository
        existingChannelEntity = channelRepository.save(existingChannelEntity);

        // Convert the updated entity to a response DTO
        return channelDtoMapper.mapToDto(existingChannelEntity);
    }

    public void deleteChannel(Long id) {
        ChannelEntity channel = getChannelEntity(id);
        // If there are any relations, remove these first by setting the fields to null.
        channelRepository.delete(channel);
    }

    // Helper: gets entity from repository
    private ChannelEntity getChannelEntity(Long id) {
        ChannelEntity channelEntity = channelRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Channel " + id + " not found"));
        return channelEntity;
    }
    
}
