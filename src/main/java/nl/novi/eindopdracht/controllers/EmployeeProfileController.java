package nl.novi.eindopdracht.controllers;

import jakarta.validation.Valid;
import nl.novi.eindopdracht.dtos.employeeProfile.EmployeeProfileRequestDto;
import nl.novi.eindopdracht.dtos.employeeProfile.EmployeeProfileResponseDto;
import nl.novi.eindopdracht.helpers.UrlHelper;
import nl.novi.eindopdracht.services.EmployeeProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee-profiles")
public class EmployeeProfileController {

    private final EmployeeProfileService employeeProfileService;
    private final UrlHelper urlHelper;

    public EmployeeProfileController(
            EmployeeProfileService employeeProfileService,
            UrlHelper urlHelper
    ) {
        this.employeeProfileService = employeeProfileService;
        this.urlHelper = urlHelper;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeProfileResponseDto>> getAllEmployeeProfiles() {
        List<EmployeeProfileResponseDto> employeeProfiles = employeeProfileService.getAllEmployeeProfiles();
        return new ResponseEntity<>(employeeProfiles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeProfileResponseDto> getEmployeeProfileById(@PathVariable Long id)  {
        EmployeeProfileResponseDto employeeProfile = employeeProfileService.getEmployeeProfileById(id);
        return new ResponseEntity<>(employeeProfile, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmployeeProfileResponseDto> createEmployeeProfile(@RequestBody  @Valid EmployeeProfileRequestDto employeeProfileRequestDto) {
        EmployeeProfileResponseDto newEmployeeProfile = employeeProfileService.createEmployeeProfile(employeeProfileRequestDto);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(newEmployeeProfile.getId())).body(newEmployeeProfile);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeProfileResponseDto> updateEmployeeProfile(@PathVariable Long id, @RequestBody  @Valid EmployeeProfileRequestDto employeeProfileRequestDto)  {
        EmployeeProfileResponseDto updatedEmployeeProfile = employeeProfileService.updateEmployeeProfile(id, employeeProfileRequestDto);
        return new ResponseEntity<>(updatedEmployeeProfile, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeeProfile(@PathVariable Long id) {
        employeeProfileService.deleteEmployeeProfile(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
