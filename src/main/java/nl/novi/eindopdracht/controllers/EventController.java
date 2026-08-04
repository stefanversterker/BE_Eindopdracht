package nl.novi.eindopdracht.controllers;

import jakarta.validation.Valid;
import nl.novi.eindopdracht.dtos.event.EventRequestDto;
import nl.novi.eindopdracht.dtos.event.EventResponseDto;
import nl.novi.eindopdracht.helpers.UrlHelper;
import nl.novi.eindopdracht.services.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;
    private final UrlHelper urlHelper;

    public EventController(
            EventService eventService,
            UrlHelper urlHelper
    ) {
        this.eventService = eventService;
        this.urlHelper = urlHelper;
    }

    @GetMapping
    public ResponseEntity<List<EventResponseDto>> getAllEvents() {
        List<EventResponseDto> events = eventService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDto> getEventById(@PathVariable Long id)  {
        EventResponseDto event = eventService.getEventById(id);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EventResponseDto> createEvent(@RequestBody  @Valid EventRequestDto eventRequestDto) {
        EventResponseDto newEvent = eventService.createEvent(eventRequestDto);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(newEvent.getId())).body(newEvent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventResponseDto> updateEvent(@PathVariable Long id, @RequestBody  @Valid EventRequestDto eventRequestDto)  {
        EventResponseDto updatedEvent = eventService.updateEvent(id, eventRequestDto);
        return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
