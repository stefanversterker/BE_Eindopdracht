package nl.novi.eindopdracht.controllers;

import jakarta.validation.Valid;
import nl.novi.eindopdracht.dtos.person.PersonRequestDto;
import nl.novi.eindopdracht.dtos.person.PersonResponseDto;
import nl.novi.eindopdracht.helpers.UrlHelper;
import nl.novi.eindopdracht.services.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;
    private final UrlHelper urlHelper;

    public PersonController(
            PersonService personService,
            UrlHelper urlHelper
    ) {
        this.personService = personService;
        this.urlHelper = urlHelper;
    }

    @GetMapping
    public ResponseEntity<List<PersonResponseDto>> getAllPersons() {
        List<PersonResponseDto> persons = personService.getAllPersons();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonResponseDto> getPersonById(@PathVariable Long id)  {
        PersonResponseDto person = personService.getPersonById(id);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PersonResponseDto> createPerson(@RequestBody  @Valid PersonRequestDto personRequestDto) {
        PersonResponseDto newPerson = personService.createPerson(personRequestDto);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(newPerson.getId())).body(newPerson);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonResponseDto> updatePerson(@PathVariable Long id, @RequestBody  @Valid PersonRequestDto personRequestDto)  {
        PersonResponseDto updatedPerson = personService.updatePerson(id, personRequestDto);
        return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
