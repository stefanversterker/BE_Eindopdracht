package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.dtos.employeeProfile.EmployeeProfileRequestDto;
import nl.novi.eindopdracht.dtos.employeeProfile.EmployeeProfileResponseDto;
import nl.novi.eindopdracht.entities.EmployeeProfileEntity;
import nl.novi.eindopdracht.entities.PersonEntity;
import nl.novi.eindopdracht.exceptions.DuplicateRecordException;
import nl.novi.eindopdracht.exceptions.RecordNotFoundException;
import nl.novi.eindopdracht.mappers.EmployeeProfileDtoMapper;
import nl.novi.eindopdracht.repositories.EmployeeProfileRepository;
import nl.novi.eindopdracht.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeProfileServiceTest {

    @Mock
    private EmployeeProfileRepository employeeProfileRepository;

    @Mock
    private EmployeeProfileDtoMapper employeeProfileDtoMapper;

    @Mock
    private PersonRepository personRepository;

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
                        personRepository
                );

        person = new PersonEntity();
        person.setId(1L);

        employeeProfile = new EmployeeProfileEntity();
        employeeProfile.setId(1L);
        employeeProfile.setPersonEntity(person);

        responseDto = new EmployeeProfileResponseDto();
        responseDto.setId(1L);
        responseDto.setPersonId(1L);

        requestDto = new EmployeeProfileRequestDto();
        requestDto.setPersonId(1L);
    }

    @Test
    void getAllEmployeeProfiles_shouldReturnListOfEmployeeProfiles() {
        // Arrange
        List<EmployeeProfileEntity> entities = List.of(employeeProfile);
        List<EmployeeProfileResponseDto> dtos = List.of(responseDto);

        when(employeeProfileRepository.findAll())
                .thenReturn(entities);

        when(employeeProfileDtoMapper.mapToDto(entities))
                .thenReturn(dtos);

        // Act
        List<EmployeeProfileResponseDto> result =
                employeeProfileService.getAllEmployeeProfiles();

        // Assert
        assertEquals(1, result.size());

        verify(employeeProfileRepository).findAll();
        verify(employeeProfileDtoMapper).mapToDto(entities);
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

    @Test
    void getEmployeeProfileById_shouldThrowRecordNotFoundException_WhenIdDoesNotExist() {
        //Arrange
        when(employeeProfileRepository.findById(1L))
                .thenReturn(Optional.empty());

        //Act + Assert
        RecordNotFoundException exception =
                assertThrows(
                        RecordNotFoundException.class,
                        () -> employeeProfileService.getEmployeeProfileById(1L)
                );

        assertEquals(
                "EmployeeProfile with id 1 not found.",
                exception.getMessage()
        );
    }

    @Test
    void createEmployeeProfile_shouldCreateEmployeeProfile_WhenRequestIsValid() {
        //Arrange
        when(employeeProfileDtoMapper.mapToEntity(requestDto))
                .thenReturn(employeeProfile);

        when(employeeProfileRepository.existsByPersonEntityId(
                1L
        ))
                .thenReturn(false);

        when(personRepository.findById(1L))
                .thenReturn(Optional.of(person));

        when(employeeProfileRepository.save(employeeProfile))
                .thenReturn(employeeProfile);

        when(employeeProfileDtoMapper.mapToDto(employeeProfile))
                .thenReturn(responseDto);

        // Act
        EmployeeProfileResponseDto result =
                employeeProfileService.createEmployeeProfile(requestDto);

        // Assert
        assertEquals(1L, result.getId());

        verify(employeeProfileDtoMapper).mapToEntity(requestDto);
        verify(employeeProfileRepository).existsByPersonEntityId(1L);
        verify(personRepository).findById(1L);
        verify(employeeProfileRepository).save(employeeProfile);
        verify(employeeProfileDtoMapper).mapToDto(employeeProfile);
    }

    @Test
    void createEmployeeProfile_shouldThrowDuplicateRecordException_WhenPersonAlreadyHasEmployeeProfile() {
        // Arrange
        when(employeeProfileDtoMapper.mapToEntity(requestDto))
                .thenReturn(employeeProfile);

        when(employeeProfileRepository.existsByPersonEntityId(
                1L
        ))
                .thenReturn(true);

        // Act + Assert
        DuplicateRecordException exception =
                assertThrows(
                        DuplicateRecordException.class,
                        () -> employeeProfileService.createEmployeeProfile(requestDto)
                );

        assertEquals(
                "This person already has an employee profile.",
                exception.getMessage()
        );

        verify(employeeProfileRepository).existsByPersonEntityId(1L);
        verify(personRepository, never()).findById(anyLong());
        verify(employeeProfileRepository, never()).save(any());
    }

    @Test
    void createEmployeeProfile_shouldThrowRecordNotFoundException_WhenPersonDoesNotExist() {
        // Arrange
        when(employeeProfileDtoMapper.mapToEntity(requestDto))
                .thenReturn(employeeProfile);

        when(employeeProfileRepository.existsByPersonEntityId(
                1L
        ))
                .thenReturn(false);

        when(personRepository.findById(1L))
                .thenReturn(Optional.empty());

        // Act + Assert
        RecordNotFoundException exception =
                assertThrows(
                        RecordNotFoundException.class,
                        () -> employeeProfileService.createEmployeeProfile(requestDto)
                );

        assertEquals(
                "Person with id 1 not found.",
                exception.getMessage()
        );

        verify(employeeProfileDtoMapper).mapToEntity(requestDto);

        verify(employeeProfileRepository)
                .existsByPersonEntityId(1L);

        verify(personRepository)
                .findById(1L);

        verify(employeeProfileRepository, never())
                .save(any());

        verify(employeeProfileDtoMapper, never())
                .mapToDto(any(EmployeeProfileEntity.class));
    }

    @Test
    void updateEmployeeProfile_shouldUpdateEmployeeProfile_WhenRequestIsValid() {
        // Arrange
        when(employeeProfileRepository.findById(1L))
                .thenReturn(Optional.of(employeeProfile));

        when(personRepository.findById(1L))
                .thenReturn(Optional.of(person));

        when(employeeProfileRepository.save(employeeProfile))
                .thenReturn(employeeProfile);

        when(employeeProfileDtoMapper.mapToDto(employeeProfile))
                .thenReturn(responseDto);

        // Act
        EmployeeProfileResponseDto result =
                employeeProfileService.updateEmployeeProfile(1L, requestDto);

        // Assert
        assertEquals(1L, result.getId());
        //assertEquals(1L, result.getPersonId());

        verify(employeeProfileRepository).findById(1L);
        verify(personRepository).findById(1L);
        verify(employeeProfileRepository).save(employeeProfile);
        verify(employeeProfileDtoMapper).mapToDto(employeeProfile);

    }

    @Test
    void updateEmployeeProfile_shouldThrowRecordNotFoundException_WhenEmployeeProfileDoesNotExist() {
        // Arrange
        when(employeeProfileRepository.findById(1L))
                .thenReturn(Optional.empty());

        // Act + Assert
        RecordNotFoundException exception =
                assertThrows(
                        RecordNotFoundException.class,
                        () -> employeeProfileService.updateEmployeeProfile(1L, requestDto)
                );

        assertEquals(
                "EmployeeProfile with id 1 not found.",
                exception.getMessage()
        );

        verify(employeeProfileRepository).findById(1L);
        verify(personRepository, never()).findById(anyLong());
        verify(employeeProfileRepository, never()).save(any(EmployeeProfileEntity.class));
        verify(employeeProfileDtoMapper, never()).mapToDto(any(EmployeeProfileEntity.class));
    }

    @Test
    void updateEmployeeProfile_shouldThrowRecordNotFoundException_WhenPersonDoesNotExist() {
        // Arrange
        when(employeeProfileRepository.findById(1L))
                .thenReturn(Optional.of(employeeProfile));

        when(personRepository.findById(1L))
                .thenReturn(Optional.empty());

        // Act + Assert
        RecordNotFoundException exception =
                assertThrows(
                        RecordNotFoundException.class,
                        () -> employeeProfileService.updateEmployeeProfile(1L, requestDto)
                );

        assertEquals(
                "Person with id 1 not found.",
                exception.getMessage()
        );

        verify(employeeProfileRepository).findById(1L);
        verify(personRepository).findById(1L);
        verify(employeeProfileRepository, never()).save(any(EmployeeProfileEntity.class));
        verify(employeeProfileDtoMapper, never()).mapToDto(any(EmployeeProfileEntity.class));
    }

    @Test
    void deleteEmployeeProfile_shouldDeleteEmployeeProfile_WhenEmployeeProfileExists() {
        // Arrange
        employeeProfile.setPersonEntity(person);

        when(employeeProfileRepository.findById(1L))
                .thenReturn(Optional.of(employeeProfile));

        // Act
        employeeProfileService.deleteEmployeeProfile(1L);

        // Assert
        verify(employeeProfileRepository).delete(employeeProfile);

        assertNull(employeeProfile.getPersonEntity());
    }

    @Test
    void deleteEmployeeProfile_shouldThrowRecordNotFoundException_WhenEmployeeProfileDoesNotExist() {
        //Arrange
        when(employeeProfileRepository.findById(1L))
                .thenReturn(Optional.empty());

        // Act + Assert
        RecordNotFoundException exception =
                assertThrows(
                        RecordNotFoundException.class,
                        () -> employeeProfileService.deleteEmployeeProfile(1L)
                );

        assertEquals(
                "EmployeeProfile with id 1 not found.",
                exception.getMessage()
        );

        verify(employeeProfileRepository).findById(1L);

        verify(employeeProfileRepository, never())
                .delete(any(EmployeeProfileEntity.class));
    }

    @Test
    void deleteEmployeeProfile_shouldRemovePersonReferenceBeforeDeleting() {
        // Arrange
        employeeProfile.setPersonEntity(person);

        when(employeeProfileRepository.findById(1L))
                .thenReturn(Optional.of(employeeProfile));

        // Act
        employeeProfileService.deleteEmployeeProfile(1L);

        // Assert
        assertNull(employeeProfile.getPersonEntity());

        verify(employeeProfileRepository).delete(employeeProfile);
    }
}