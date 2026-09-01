package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.dtos.channel.ChannelRequestDto;
import nl.novi.eindopdracht.dtos.channel.ChannelResponseDto;
import nl.novi.eindopdracht.entities.ChannelEntity;
import nl.novi.eindopdracht.entities.MixerEntity;
import nl.novi.eindopdracht.entities.SourceEntity;
import nl.novi.eindopdracht.exceptions.RecordNotFoundException;
import nl.novi.eindopdracht.mappers.ChannelDtoMapper;
import nl.novi.eindopdracht.repositories.ChannelRepository;
import nl.novi.eindopdracht.repositories.MixerRepository;
import nl.novi.eindopdracht.repositories.SourceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChannelService {

    private final ChannelRepository channelRepository;
    private final ChannelDtoMapper channelDtoMapper;
    private final SourceRepository sourceRepository;
    private final MixerRepository mixerRepository;


    public ChannelService(
            ChannelRepository channelRepository,
            ChannelDtoMapper channelDtoMapper,
            SourceRepository sourceRepository,
            MixerRepository mixerRepository
    ) {
        this.channelRepository = channelRepository;
        this.channelDtoMapper = channelDtoMapper;
        this.sourceRepository = sourceRepository;
        this.mixerRepository = mixerRepository;
    }

    @Transactional(readOnly = true)
    public List<ChannelResponseDto> getAllChannels() {
        return channelDtoMapper.mapToDto(channelRepository.findAll());
    }

    @Transactional(readOnly = true)
    public ChannelResponseDto getChannelById(long id) {
        return channelDtoMapper.mapToDto(getChannelEntity(id));
    }

    public List<ChannelResponseDto> getChannelsByMixer(Long mixerId) {
        return channelDtoMapper.mapToDto(
                channelRepository.findByMixerEntityId(mixerId)
        );
    }

    public ChannelResponseDto createChannel(
            ChannelRequestDto channelRequestDto,
            Long mixerId) {

        // Create the entity the repository expects
        ChannelEntity channel = channelDtoMapper.mapToEntity(channelRequestDto);

        // Extract sourceId
        Long sourceId = channelRequestDto.getSourceId();

        // Find sourceEntity
        if (sourceId != null) {
            SourceEntity source = getSourceEntity(sourceId);

            // Set the related source
            channel.setSourceEntity(source);
        }

        // Find mixerEntity
        if (mixerId != null) {
            MixerEntity mixer = getMixerEntity(mixerId);

            // Set the related mixer
            channel.setMixerEntity(mixer);
        }

        // Save the entity in the repository
        channel = channelRepository.save(channel);

        // Convert the saved entity to a response DTO
        return channelDtoMapper.mapToDto(channel);
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

    private MixerEntity getMixerEntity(Long id) {
        return mixerRepository.findById(id)
                .orElseThrow(() ->
                        new RecordNotFoundException("Mixer with id " + id + " not found."));
    }

    private SourceEntity getSourceEntity(Long id) {
        return sourceRepository.findById(id)
                .orElseThrow(() ->
                        new RecordNotFoundException("Source with id " + id + " not found."));
    }
}
