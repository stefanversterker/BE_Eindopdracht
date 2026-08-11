package nl.novi.eindopdracht.controllers;

import jakarta.validation.Valid;
import nl.novi.eindopdracht.dtos.performerAct.PerformerActRequestDto;
import nl.novi.eindopdracht.dtos.performerAct.PerformerActResponseDto;
import nl.novi.eindopdracht.helpers.UrlHelper;
import nl.novi.eindopdracht.services.PerformerActService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/performer-acts")
public class PerformerActController {

    private final PerformerActService performerActService;
    private final UrlHelper urlHelper;

    public PerformerActController(
            PerformerActService performerActService,
            UrlHelper urlHelper
    ) {
        this.performerActService = performerActService;
        this.urlHelper = urlHelper;
    }

    @GetMapping
    public ResponseEntity<List<PerformerActResponseDto>> getAllPerformerActs() {
        List<PerformerActResponseDto> performerActs = performerActService.getAllPerformerActs();
        return new ResponseEntity<>(performerActs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerformerActResponseDto> getPerformerActById(@PathVariable Long id)  {
        PerformerActResponseDto performerAct = performerActService.getPerformerActById(id);
        return new ResponseEntity<>(performerAct, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PerformerActResponseDto> createPerformerAct(@RequestBody  @Valid PerformerActRequestDto performerActRequestDto) {
        PerformerActResponseDto newPerformerAct = performerActService.createPerformerAct(performerActRequestDto);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(newPerformerAct.getId())).body(newPerformerAct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PerformerActResponseDto> updatePerformerAct(@PathVariable Long id, @RequestBody  @Valid PerformerActRequestDto performerActRequestDto)  {
        PerformerActResponseDto updatedPerformerAct = performerActService.updatePerformerAct(id, performerActRequestDto);
        return new ResponseEntity<>(updatedPerformerAct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerformerAct(@PathVariable Long id) {
        performerActService.deletePerformerAct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
