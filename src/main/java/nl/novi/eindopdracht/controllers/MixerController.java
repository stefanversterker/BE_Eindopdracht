package nl.novi.eindopdracht.controllers;

import jakarta.validation.Valid;
import nl.novi.eindopdracht.dtos.channel.ChannelRequestDto;
import nl.novi.eindopdracht.dtos.channel.ChannelResponseDto;
import nl.novi.eindopdracht.dtos.mixer.MixerRequestDto;
import nl.novi.eindopdracht.dtos.mixer.MixerResponseDto;
import nl.novi.eindopdracht.helpers.UrlHelper;
import nl.novi.eindopdracht.services.ChannelService;
import nl.novi.eindopdracht.services.MixerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mixers")
public class MixerController {

    private final MixerService mixerService;
    private final ChannelService channelService;
    private final UrlHelper urlHelper;

    public MixerController(
            MixerService mixerService,
            UrlHelper urlHelper,
            ChannelService channelService
    ) {
        this.mixerService = mixerService;
        this.urlHelper = urlHelper;
        this.channelService =channelService;
    }

    @GetMapping
    public ResponseEntity<List<MixerResponseDto>> getAllMixers() {
        List<MixerResponseDto> mixers = mixerService.getAllMixers();
        return new ResponseEntity<>(mixers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MixerResponseDto> getMixerById(@PathVariable Long id)  {
        MixerResponseDto mixer = mixerService.getMixerById(id);
        return new ResponseEntity<>(mixer, HttpStatus.OK);
    }

    @GetMapping("/{mixerId}/channels")
    public ResponseEntity<List<ChannelResponseDto>> getChannelsByMixer(
            @PathVariable Long mixerId) {

        return ResponseEntity.ok(
                channelService.getChannelsByMixer(mixerId)
        );
    }

    @PostMapping
    public ResponseEntity<MixerResponseDto> createMixer(@RequestBody  @Valid MixerRequestDto mixerRequestDto) {
        MixerResponseDto newMixer = mixerService.createMixer(mixerRequestDto);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(newMixer.getId())).body(newMixer);
    }

    @PostMapping("/{mixerId}/channels")
    public ResponseEntity<ChannelResponseDto> createChannel(
            @PathVariable Long mixerId,
            @RequestBody  @Valid ChannelRequestDto channelRequestDto) {
        ChannelResponseDto newChannel = channelService.createChannel(channelRequestDto, mixerId);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(newChannel.getId())).body(newChannel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MixerResponseDto> updateMixer(@PathVariable Long id, @RequestBody  @Valid MixerRequestDto mixerRequestDto)  {
        MixerResponseDto updatedMixer = mixerService.updateMixer(id, mixerRequestDto);
        return new ResponseEntity<>(updatedMixer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMixer(@PathVariable Long id) {
        mixerService.deleteMixer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
