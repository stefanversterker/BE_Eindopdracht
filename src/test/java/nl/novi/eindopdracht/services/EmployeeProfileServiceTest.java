package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.dtos.employeeProfile.EmployeeProfileRequestDto;
import nl.novi.eindopdracht.dtos.employeeProfile.EmployeeProfileResponseDto;
import nl.novi.eindopdracht.entities.EmployeeProfileEntity;
import nl.novi.eindopdracht.entities.PersonEntity;
import nl.novi.eindopdracht.mappers.EmployeeProfileDtoMapper;
import nl.novi.eindopdracht.repositories.EmployeeProfileRepository;
import nl.novi.eindopdracht.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeProfileServiceTest {

    @Mock
    private EmployeeProfileRepository employeeProfileRepository;

    @Mock
    private EmployeeProfileDtoMapper employeeProfileDtoMapper;

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private EmployeeProfileService employeeProfileService;

    // Test data
    private PersonEntity person;
    private EmployeeProfileEntity employeeProfile;
    private EmployeeProfileRequestDto requestDto;
    private EmployeeProfileResponseDto responseDto;


    @BeforeEach
    void setUp() {

        employeeProfileService =
                new EmployeeProfileService(
                        employeeProfileRepository,
                        employeeProfileDtoMapper,
                        personRepository);

        person = new PersonEntity();
        person.setId(1L);

        employeeProfile = new EmployeeProfileEntity();
        employeeProfile.setId(1L);
        employeeProfile.setPersonEntity(person);

        responseDto = new EmployeeProfileResponseDto();
        responseDto.setId(1L);

        requestDto = new EmployeeProfileRequestDto();
        requestDto.setPersonId(1L);
    }

    @Test
    void getEmployeeProfileById_shouldReturnEmployeeProfileDto() {
        // Arrange
        when(employeeProfileRepository.findById(1L))
                .thenReturn(Optional.of(employeeProfile));

        when(employeeProfileDtoMapper.mapToDto(employeeProfile))
                .thenReturn(responseDto);

        // Act
        EmployeeProfileResponseDto result = employeeProfileService.getEmployeeProfileById(1L);

        // Assert
        assertEquals(1L, result.getId());
        verify(employeeProfileRepository).findById(1L);
        verify(employeeProfileDtoMapper).mapToDto(employeeProfile);
    }

}