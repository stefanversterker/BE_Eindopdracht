package nl.novi.eindopdracht.controllers;

import jakarta.validation.Valid;
import nl.novi.eindopdracht.dtos.performerProfile.PerformerProfileRequestDto;
import nl.novi.eindopdracht.dtos.performerProfile.PerformerProfileResponseDto;
import nl.novi.eindopdracht.helpers.UrlHelper;
import nl.novi.eindopdracht.services.PerformerProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/performer-profiles")
public class PerformerProfileController {

    private final PerformerProfileService performerProfileService;
    private final UrlHelper urlHelper;

    public PerformerProfileController(
            PerformerProfileService performerProfileService,
            UrlHelper urlHelper
    ) {
        this.performerProfileService = performerProfileService;
        this.urlHelper = urlHelper;
    }

    @GetMapping
    public ResponseEntity<List<PerformerProfileResponseDto>> getAllPerformerProfiles() {
        List<PerformerProfileResponseDto> performerProfiles = performerProfileService.getAllPerformerProfiles();
        return new ResponseEntity<>(performerProfiles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerformerProfileResponseDto> getPerformerProfileById(@PathVariable Long id)  {
        PerformerProfileResponseDto performerProfile = performerProfileService.getPerformerProfileById(id);
        return new ResponseEntity<>(performerProfile, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PerformerProfileResponseDto> createPerformerProfile(@RequestBody  @Valid PerformerProfileRequestDto performerProfileRequestDto) {
        PerformerProfileResponseDto newPerformerProfile = performerProfileService.createPerformerProfile(performerProfileRequestDto);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(newPerformerProfile.getId())).body(newPerformerProfile);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PerformerProfileResponseDto> updatePerformerProfile(@PathVariable Long id, @RequestBody  @Valid PerformerProfileRequestDto performerProfileRequestDto)  {
        PerformerProfileResponseDto updatedPerformerProfile = performerProfileService.updatePerformerProfile(id, performerProfileRequestDto);
        return new ResponseEntity<>(updatedPerformerProfile, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerformerProfile(@PathVariable Long id) {
        performerProfileService.deletePerformerProfile(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
