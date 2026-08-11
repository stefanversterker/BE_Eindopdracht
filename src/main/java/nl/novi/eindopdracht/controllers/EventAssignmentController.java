package nl.novi.eindopdracht.controllers;

import jakarta.validation.Valid;
import nl.novi.eindopdracht.dtos.eventAssignment.EventAssignmentRequestDto;
import nl.novi.eindopdracht.dtos.eventAssignment.EventAssignmentResponseDto;
import nl.novi.eindopdracht.helpers.UrlHelper;
import nl.novi.eindopdracht.services.EventAssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event-assignments")
public class EventAssignmentController {

    private final EventAssignmentService eventAssignmentService;
    private final UrlHelper urlHelper;

    public EventAssignmentController(
            EventAssignmentService eventAssignmentService,
            UrlHelper urlHelper
    ) {
        this.eventAssignmentService = eventAssignmentService;
        this.urlHelper = urlHelper;
    }

    @GetMapping
    public ResponseEntity<List<EventAssignmentResponseDto>> getAllEventAssignments() {
        List<EventAssignmentResponseDto> eventAssignments = eventAssignmentService.getAllEventAssignments();
        return new ResponseEntity<>(eventAssignments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventAssignmentResponseDto> getEventAssignmentById(@PathVariable Long id)  {
        EventAssignmentResponseDto eventAssignment = eventAssignmentService.getEventAssignmentById(id);
        return new ResponseEntity<>(eventAssignment, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EventAssignmentResponseDto> createEventAssignment(@RequestBody  @Valid EventAssignmentRequestDto eventAssignmentRequestDto) {
        EventAssignmentResponseDto newEventAssignment = eventAssignmentService.createEventAssignment(eventAssignmentRequestDto);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(newEventAssignment.getId())).body(newEventAssignment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventAssignmentResponseDto> updateEventAssignment(@PathVariable Long id, @RequestBody  @Valid EventAssignmentRequestDto eventAssignmentRequestDto)  {
        EventAssignmentResponseDto updatedEventAssignment = eventAssignmentService.updateEventAssignment(id, eventAssignmentRequestDto);
        return new ResponseEntity<>(updatedEventAssignment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEventAssignment(@PathVariable Long id) {
        eventAssignmentService.deleteEventAssignment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
