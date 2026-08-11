package nl.novi.eindopdracht.controllers;

import jakarta.validation.Valid;
import nl.novi.eindopdracht.dtos.performance.PerformanceRequestDto;
import nl.novi.eindopdracht.dtos.performance.PerformanceResponseDto;
import nl.novi.eindopdracht.helpers.UrlHelper;
import nl.novi.eindopdracht.services.PerformanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/performances")
public class PerformanceController {

    private final PerformanceService performanceService;
    private final UrlHelper urlHelper;

    public PerformanceController(
            PerformanceService performanceService,
            UrlHelper urlHelper
    ) {
        this.performanceService = performanceService;
        this.urlHelper = urlHelper;
    }

    @GetMapping
    public ResponseEntity<List<PerformanceResponseDto>> getAllPerformances() {
        List<PerformanceResponseDto> performances = performanceService.getAllPerformances();
        return new ResponseEntity<>(performances, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerformanceResponseDto> getPerformanceById(@PathVariable Long id)  {
        PerformanceResponseDto performance = performanceService.getPerformanceById(id);
        return new ResponseEntity<>(performance, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PerformanceResponseDto> createPerformance(@RequestBody  @Valid PerformanceRequestDto performanceRequestDto) {
        PerformanceResponseDto newPerformance = performanceService.createPerformance(performanceRequestDto);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(newPerformance.getId())).body(newPerformance);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PerformanceResponseDto> updatePerformance(@PathVariable Long id, @RequestBody  @Valid PerformanceRequestDto performanceRequestDto)  {
        PerformanceResponseDto updatedPerformance = performanceService.updatePerformance(id, performanceRequestDto);
        return new ResponseEntity<>(updatedPerformance, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerformance(@PathVariable Long id) {
        performanceService.deletePerformance(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
