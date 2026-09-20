package nl.novi.eindopdracht.controllers;

import jakarta.validation.Valid;
import nl.novi.eindopdracht.dtos.channel.ChannelRequestDto;
import nl.novi.eindopdracht.dtos.channel.ChannelResponseDto;
import nl.novi.eindopdracht.helpers.UrlHelper;
import nl.novi.eindopdracht.services.ChannelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/channels")
public class ChannelController {

    private final ChannelService channelService;
    private final UrlHelper urlHelper;

    public ChannelController(
            ChannelService channelService,
            UrlHelper urlHelper
    ) {
        this.channelService = channelService;
        this.urlHelper = urlHelper;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ResponseEntity<List<ChannelResponseDto>> getAllChannels() {
        List<ChannelResponseDto> channels = channelService.getAllChannels();
        return new ResponseEntity<>(channels, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<ChannelResponseDto> getChannelById(@PathVariable Long id)  {
        ChannelResponseDto channel = channelService.getChannelById(id);
        return new ResponseEntity<>(channel, HttpStatus.OK);
    }

    // No PostMapping because channels are created as part of a mixer

    @PreAuthorize("hasRole('ENGINEER')")
    @PutMapping("/{id}")
    public ResponseEntity<ChannelResponseDto> updateChannel(@PathVariable Long id, @RequestBody  @Valid ChannelRequestDto channelRequestDto)  {
        ChannelResponseDto updatedChannel = channelService.updateChannel(id, channelRequestDto);
        return new ResponseEntity<>(updatedChannel, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ENGINEER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChannel(@PathVariable Long id) {
        channelService.deleteChannel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
