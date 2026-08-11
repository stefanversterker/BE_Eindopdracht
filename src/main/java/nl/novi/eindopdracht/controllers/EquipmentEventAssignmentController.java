package nl.novi.eindopdracht.controllers;

import jakarta.validation.Valid;
import nl.novi.eindopdracht.dtos.equipmentEventAssignment.EquipmentEventAssignmentRequestDto;
import nl.novi.eindopdracht.dtos.equipmentEventAssignment.EquipmentEventAssignmentResponseDto;
import nl.novi.eindopdracht.helpers.UrlHelper;
import nl.novi.eindopdracht.services.EquipmentEventAssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipment-event-assignments")
public class EquipmentEventAssignmentController {

    private final EquipmentEventAssignmentService equipmentEventAssignmentService;
    private final UrlHelper urlHelper;

    public EquipmentEventAssignmentController(
            EquipmentEventAssignmentService equipmentEventAssignmentService,
            UrlHelper urlHelper
    ) {
        this.equipmentEventAssignmentService = equipmentEventAssignmentService;
        this.urlHelper = urlHelper;
    }

    @GetMapping
    public ResponseEntity<List<EquipmentEventAssignmentResponseDto>> getAllEquipmentEventAssignments() {
        List<EquipmentEventAssignmentResponseDto> equipmentEventAssignments = equipmentEventAssignmentService.getAllEquipmentEventAssignments();
        return new ResponseEntity<>(equipmentEventAssignments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentEventAssignmentResponseDto> getEquipmentEventAssignmentById(@PathVariable Long id)  {
        EquipmentEventAssignmentResponseDto equipmentEventAssignment = equipmentEventAssignmentService.getEquipmentEventAssignmentById(id);
        return new ResponseEntity<>(equipmentEventAssignment, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EquipmentEventAssignmentResponseDto> createEquipmentEventAssignment(@RequestBody  @Valid EquipmentEventAssignmentRequestDto equipmentEventAssignmentRequestDto) {
        EquipmentEventAssignmentResponseDto newEquipmentEventAssignment = equipmentEventAssignmentService.createEquipmentEventAssignment(equipmentEventAssignmentRequestDto);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(newEquipmentEventAssignment.getId())).body(newEquipmentEventAssignment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipmentEventAssignmentResponseDto> updateEquipmentEventAssignment(@PathVariable Long id, @RequestBody  @Valid EquipmentEventAssignmentRequestDto equipmentEventAssignmentRequestDto)  {
        EquipmentEventAssignmentResponseDto updatedEquipmentEventAssignment = equipmentEventAssignmentService.updateEquipmentEventAssignment(id, equipmentEventAssignmentRequestDto);
        return new ResponseEntity<>(updatedEquipmentEventAssignment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipmentEventAssignment(@PathVariable Long id) {
        equipmentEventAssignmentService.deleteEquipmentEventAssignment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
