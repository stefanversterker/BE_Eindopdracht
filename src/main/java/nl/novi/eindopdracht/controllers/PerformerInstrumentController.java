package nl.novi.eindopdracht.controllers;

import jakarta.validation.Valid;
import nl.novi.eindopdracht.dtos.performerInstrument.PerformerInstrumentRequestDto;
import nl.novi.eindopdracht.dtos.performerInstrument.PerformerInstrumentResponseDto;
import nl.novi.eindopdracht.helpers.UrlHelper;
import nl.novi.eindopdracht.services.PerformerInstrumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/performer-instruments")
public class PerformerInstrumentController {

    private final PerformerInstrumentService performerInstrumentService;
    private final UrlHelper urlHelper;

    public PerformerInstrumentController(
            PerformerInstrumentService performerInstrumentService,
            UrlHelper urlHelper
    ) {
        this.performerInstrumentService = performerInstrumentService;
        this.urlHelper = urlHelper;
    }

    @GetMapping
    public ResponseEntity<List<PerformerInstrumentResponseDto>> getAllPerformerInstruments() {
        List<PerformerInstrumentResponseDto> performerInstruments = performerInstrumentService.getAllPerformerInstruments();
        return new ResponseEntity<>(performerInstruments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerformerInstrumentResponseDto> getPerformerInstrumentById(@PathVariable Long id)  {
        PerformerInstrumentResponseDto performerInstrument = performerInstrumentService.getPerformerInstrumentById(id);
        return new ResponseEntity<>(performerInstrument, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PerformerInstrumentResponseDto> createPerformerInstrument(@RequestBody  @Valid PerformerInstrumentRequestDto performerInstrumentRequestDto) {
        PerformerInstrumentResponseDto newPerformerInstrument = performerInstrumentService.createPerformerInstrument(performerInstrumentRequestDto);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(newPerformerInstrument.getId())).body(newPerformerInstrument);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PerformerInstrumentResponseDto> updatePerformerInstrument(@PathVariable Long id, @RequestBody  @Valid PerformerInstrumentRequestDto performerInstrumentRequestDto)  {
        PerformerInstrumentResponseDto updatedPerformerInstrument = performerInstrumentService.updatePerformerInstrument(id, performerInstrumentRequestDto);
        return new ResponseEntity<>(updatedPerformerInstrument, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerformerInstrument(@PathVariable Long id) {
        performerInstrumentService.deletePerformerInstrument(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
