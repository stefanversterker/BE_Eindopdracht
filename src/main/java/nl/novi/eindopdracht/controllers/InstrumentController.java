package nl.novi.eindopdracht.controllers;

import jakarta.validation.Valid;
import nl.novi.eindopdracht.dtos.instrument.InstrumentRequestDto;
import nl.novi.eindopdracht.dtos.instrument.InstrumentResponseDto;
import nl.novi.eindopdracht.helpers.UrlHelper;
import nl.novi.eindopdracht.services.InstrumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instruments")
public class InstrumentController {

    private final InstrumentService instrumentService;
    private final UrlHelper urlHelper;

    public InstrumentController(
            InstrumentService instrumentService,
            UrlHelper urlHelper
    ) {
        this.instrumentService = instrumentService;
        this.urlHelper = urlHelper;
    }

    @GetMapping
    public ResponseEntity<List<InstrumentResponseDto>> getAllInstruments() {
        List<InstrumentResponseDto> instruments = instrumentService.getAllInstruments();
        return new ResponseEntity<>(instruments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstrumentResponseDto> getInstrumentById(@PathVariable Long id)  {
        InstrumentResponseDto instrument = instrumentService.getInstrumentById(id);
        return new ResponseEntity<>(instrument, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<InstrumentResponseDto> createInstrument(@RequestBody  @Valid InstrumentRequestDto instrumentRequestDto) {
        InstrumentResponseDto newInstrument = instrumentService.createInstrument(instrumentRequestDto);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(newInstrument.getId())).body(newInstrument);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstrumentResponseDto> updateInstrument(@PathVariable Long id, @RequestBody  @Valid InstrumentRequestDto instrumentRequestDto)  {
        InstrumentResponseDto updatedInstrument = instrumentService.updateInstrument(id, instrumentRequestDto);
        return new ResponseEntity<>(updatedInstrument, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstrument(@PathVariable Long id) {
        instrumentService.deleteInstrument(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
