package nl.novi.eindopdracht.controllers;

import jakarta.validation.Valid;
import nl.novi.eindopdracht.dtos.mixer.MixerRequestDto;
import nl.novi.eindopdracht.dtos.mixer.MixerResponseDto;
import nl.novi.eindopdracht.helpers.UrlHelper;
import nl.novi.eindopdracht.services.MixerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mixers")
public class MixerController {

    private final MixerService mixerService;
    private final UrlHelper urlHelper;

    public MixerController(
            MixerService mixerService,
            UrlHelper urlHelper
    ) {
        this.mixerService = mixerService;
        this.urlHelper = urlHelper;
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

    @PostMapping
    public ResponseEntity<MixerResponseDto> createMixer(@RequestBody  @Valid MixerRequestDto mixerRequestDto) {
        MixerResponseDto newMixer = mixerService.createMixer(mixerRequestDto);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(newMixer.getId())).body(newMixer);
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
