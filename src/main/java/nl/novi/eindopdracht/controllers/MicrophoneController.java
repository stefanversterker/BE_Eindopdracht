package nl.novi.eindopdracht.controllers;

import jakarta.validation.Valid;
import nl.novi.eindopdracht.dtos.microphone.MicrophoneRequestDto;
import nl.novi.eindopdracht.dtos.microphone.MicrophoneResponseDto;
import nl.novi.eindopdracht.helpers.UrlHelper;
import nl.novi.eindopdracht.services.MicrophoneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/microphones")
public class MicrophoneController {

    private final MicrophoneService microphoneService;
    private final UrlHelper urlHelper;

    public MicrophoneController(
            MicrophoneService microphoneService,
            UrlHelper urlHelper
    )   {
        this.microphoneService = microphoneService;
        this.urlHelper = urlHelper;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ResponseEntity<List<MicrophoneResponseDto>> getAllMicrophones() {
        List<MicrophoneResponseDto> microphones = microphoneService.getAllMicrophones();
        return new ResponseEntity<>(microphones, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<MicrophoneResponseDto> getMicrophoneById(@PathVariable Long id)  {
        MicrophoneResponseDto microphone = microphoneService.getMicrophoneById(id);
        return new ResponseEntity<>(microphone, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ENGINEER')")
    @PostMapping
    public ResponseEntity<MicrophoneResponseDto> createMicrophone(@RequestBody  @Valid MicrophoneRequestDto microphoneRequestDto) {
        MicrophoneResponseDto newMicrophone = microphoneService.createMicrophone(microphoneRequestDto);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(newMicrophone.getId())).body(newMicrophone);
    }

    @PreAuthorize("hasRole('ENGINEER')")
    @PutMapping("/{id}")
    public ResponseEntity<MicrophoneResponseDto> updateMicrophone(@PathVariable Long id, @RequestBody  @Valid MicrophoneRequestDto microphoneRequestDto)  {
        MicrophoneResponseDto updatedMicrophone = microphoneService.updateMicrophone(id, microphoneRequestDto);
        return new ResponseEntity<>(updatedMicrophone, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ENGINEER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMicrophone(@PathVariable Long id) {
        microphoneService.deleteMicrophone(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
