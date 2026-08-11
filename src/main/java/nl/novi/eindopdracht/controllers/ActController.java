package nl.novi.eindopdracht.controllers;

import jakarta.validation.Valid;
import nl.novi.eindopdracht.dtos.act.ActRequestDto;
import nl.novi.eindopdracht.dtos.act.ActResponseDto;
import nl.novi.eindopdracht.helpers.UrlHelper;
import nl.novi.eindopdracht.services.ActService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acts")
public class ActController {

    private final ActService actService;
    private final UrlHelper urlHelper;

    public ActController(
            ActService actService,
            UrlHelper urlHelper
    ) {
        this.actService = actService;
        this.urlHelper = urlHelper;
    }

    @GetMapping
    public ResponseEntity<List<ActResponseDto>> getAllActs() {
        List<ActResponseDto> acts = actService.getAllActs();
        return new ResponseEntity<>(acts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActResponseDto> getActById(@PathVariable Long id)  {
        ActResponseDto act = actService.getActById(id);
        return new ResponseEntity<>(act, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ActResponseDto> createAct(@RequestBody  @Valid ActRequestDto actRequestDto) {
        ActResponseDto newAct = actService.createAct(actRequestDto);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(newAct.getId())).body(newAct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActResponseDto> updateAct(@PathVariable Long id, @RequestBody  @Valid ActRequestDto actRequestDto)  {
        ActResponseDto updatedAct = actService.updateAct(id, actRequestDto);
        return new ResponseEntity<>(updatedAct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAct(@PathVariable Long id) {
        actService.deleteAct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
