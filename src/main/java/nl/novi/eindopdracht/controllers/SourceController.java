package nl.novi.eindopdracht.controllers;

import jakarta.validation.Valid;
import nl.novi.eindopdracht.dtos.source.SourceRequestDto;
import nl.novi.eindopdracht.dtos.source.SourceResponseDto;
import nl.novi.eindopdracht.helpers.UrlHelper;
import nl.novi.eindopdracht.services.SourceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sources")
public class SourceController {

    private final SourceService sourceService;
    private final UrlHelper urlHelper;

    public SourceController(
            SourceService sourceService,
            UrlHelper urlHelper
    ) {
        this.sourceService = sourceService;
        this.urlHelper = urlHelper;
    }

    @GetMapping
    public ResponseEntity<List<SourceResponseDto>> getAllSources() {
        List<SourceResponseDto> sources = sourceService.getAllSources();
        return new ResponseEntity<>(sources, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SourceResponseDto> getSourceById(@PathVariable Long id)  {
        SourceResponseDto source = sourceService.getSourceById(id);
        return new ResponseEntity<>(source, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SourceResponseDto> createSource(@RequestBody  @Valid SourceRequestDto sourceRequestDto) {
        SourceResponseDto newSource = sourceService.createSource(sourceRequestDto);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(newSource.getId())).body(newSource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SourceResponseDto> updateSource(@PathVariable Long id, @RequestBody  @Valid SourceRequestDto sourceRequestDto)  {
        SourceResponseDto updatedSource = sourceService.updateSource(id, sourceRequestDto);
        return new ResponseEntity<>(updatedSource, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSource(@PathVariable Long id) {
        sourceService.deleteSource(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
