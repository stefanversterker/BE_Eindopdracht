package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.dtos.channel.ChannelRequestDto;
import nl.novi.eindopdracht.dtos.channel.ChannelResponseDto;
import nl.novi.eindopdracht.entities.ChannelEntity;
import nl.novi.eindopdracht.entities.SourceEntity;
import nl.novi.eindopdracht.exceptions.RecordNotFoundException;
import nl.novi.eindopdracht.mappers.ChannelDtoMapper;
import nl.novi.eindopdracht.repositories.ChannelRepository;
import nl.novi.eindopdracht.repositories.SourceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChannelService {

    private final ChannelRepository channelRepository;
    private final ChannelDtoMapper channelDtoMapper;
    private final SourceRepository sourceRepository;


    public ChannelService(
            ChannelRepository channelRepository,
            ChannelDtoMapper channelDtoMapper,
            SourceRepository sourceRepository
    ) {
        this.channelRepository = channelRepository;
        this.channelDtoMapper = channelDtoMapper;
        this.sourceRepository = sourceRepository;
    }

    @Transactional(readOnly = true)
    public List<ChannelResponseDto> getAllChannels() {
        return channelDtoMapper.mapToDto(channelRepository.findAll());
    }

    @Transactional(readOnly = true)
    public ChannelResponseDto getChannelById(long id) {
        return channelDtoMapper.mapToDto(getChannelEntity(id));
    }

    public ChannelResponseDto createChannel(ChannelRequestDto channelRequestDto) {
        // Create the entity the repository expects
        ChannelEntity channelEntity = channelDtoMapper.mapToEntity(channelRequestDto);

        // Extract sourceId
        Long sourceId = channelRequestDto.getSourceId();

        // Find sourceEntity
        if (sourceId != null) {
            SourceEntity source = getSourceEntity(sourceId);

            // Set the related source
            channelEntity.setSourceEntity(source);
        }

        // Save the entity in the repository
        channelEntity = channelRepository.save(channelEntity);

        // Convert the saved entity to a response DTO
        return channelDtoMapper.mapToDto(channelEntity);
    }

    public ChannelResponseDto updateChannel(Long id, ChannelRequestDto channelRequestDto) {
        // Retrieve the entity from the database with its current values
        ChannelEntity existingChannelEntity = getChannelEntity(id);

        Long sourceId = channelRequestDto.getSourceId();

        // Set number entity
        existingChannelEntity.setNumber(channelRequestDto.getNumber());

        // Verify and set sourceEntity
        if (sourceId != null) {
            SourceEntity source = getSourceEntity(sourceId);


            existingChannelEntity.setSourceEntity(source);
        } else {
            existingChannelEntity.setSourceEntity(null);
        }

        // Save update to repository
        existingChannelEntity = channelRepository.save(existingChannelEntity);

        // Convert the updated entity to a response DTO
        return channelDtoMapper.mapToDto(existingChannelEntity);
    }

    public void deleteChannel(Long id) {
        ChannelEntity channel = getChannelEntity(id);
        channelRepository.delete(channel);
    }

    // Helpers
    private ChannelEntity getChannelEntity(Long id) {
        return channelRepository.findById(id)
                .orElseThrow(() ->
                        new RecordNotFoundException("Channel with id " + id + " not found."));
    }

    private SourceEntity getSourceEntity(Long id) {
        return sourceRepository.findById(id)
                .orElseThrow(() ->
                        new RecordNotFoundException("Source with id " + id + " not found."));
    }
}
