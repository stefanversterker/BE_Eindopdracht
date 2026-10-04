package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.dtos.performerInstrument.PerformerInstrumentRequestDto;
import nl.novi.eindopdracht.dtos.performerInstrument.PerformerInstrumentResponseDto;
import nl.novi.eindopdracht.entities.InstrumentEntity;
import nl.novi.eindopdracht.entities.PerformerInstrumentEntity;
import nl.novi.eindopdracht.entities.PerformerProfileEntity;
import nl.novi.eindopdracht.exceptions.DuplicateRecordException;
import nl.novi.eindopdracht.exceptions.RecordNotFoundException;
import nl.novi.eindopdracht.mappers.PerformerInstrumentDtoMapper;
import nl.novi.eindopdracht.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PerformerInstrumentServiceTest {

    @Mock
    private PerformerInstrumentRepository performerInstrumentRepository;

    @Mock
    private PerformerInstrumentDtoMapper performerInstrumentDtoMapper;

    @Mock
    private PerformerProfileRepository performerProfileRepository;

    @Mock
    private InstrumentRepository instrumentRepository;

    private PerformerInstrumentService performerInstrumentService;

    // Test data
    private PerformerInstrumentEntity performerInstrument;
    private InstrumentEntity instrument;
    private PerformerProfileEntity performerProfile;
    private PerformerInstrumentRequestDto requestDto;
    private PerformerInstrumentResponseDto responseDto;

    @BeforeEach
    void setUp() {

        performerInstrumentService =
                new PerformerInstrumentService(
                        performerInstrumentRepository,
                        performerInstrumentDtoMapper,
                        performerProfileRepository,
                        instrumentRepository
                );

        instrument = new InstrumentEntity();
        instrument.setId(1L);

        performerProfile = new PerformerProfileEntity();
        performerProfile.setId(1L);

        performerInstrument = new PerformerInstrumentEntity();
        performerInstrument.setId(1L);
        performerInstrument.setInstrumentEntity(instrument);
        performerInstrument.setPerformerProfileEntity(performerProfile);

        responseDto = new PerformerInstrumentResponseDto();
        responseDto.setId(1L);
        responseDto.setInstrumentId(1L);
        responseDto.setPerformerProfileId(1L);

        requestDto = new PerformerInstrumentRequestDto();
        requestDto.setInstrumentId(1L);
        requestDto.setPerformerProfileId(1L);

    }

    @Test
    void getPerformerInstrumentById_shouldReturnPerformerInstrumentDto() {
        // Arrange
        when(performerInstrumentRepository.findById(1L))
                .thenReturn(Optional.of(performerInstrument));

        when(performerInstrumentDtoMapper.mapToDto(performerInstrument))
                .thenReturn(responseDto);

        // Act
        PerformerInstrumentResponseDto result = performerInstrumentService.getPerformerInstrumentById(1L);

        // Assert
        assertEquals(1L, result.getId());

        verify(performerInstrumentRepository).findById(1L);
        verify(performerInstrumentDtoMapper).mapToDto(performerInstrument);
    }

    @Test
    void getPerformerInstrumentById_shouldThrowRecordNotFoundException_WhenIdDoesNotExist() {
        // Arrange
        when(performerInstrumentRepository.findById(1L))
                .thenReturn(Optional.empty());

        // Act + Assert
        RecordNotFoundException exception =
                assertThrows(
                        RecordNotFoundException.class,
                        () -> performerInstrumentService.getPerformerInstrumentById(1L)
                );

        assertEquals(
                "PerformerInstrument with id 1 not found.",
                exception.getMessage()
        );
    }

    @Test
    void createPerformerInstrument_shouldCreatePerformerInstrument_WhenRequestIsValid() {
        // Arrange
        when(performerInstrumentDtoMapper.mapToEntity(requestDto))
                .thenReturn(performerInstrument);

        when(performerInstrumentRepository.existsByPerformerProfileEntityIdAndInstrumentEntityId(
                1L,
                1L
        ))
                .thenReturn(false);

        when(performerProfileRepository.findById(1L))
                .thenReturn(Optional.of(performerProfile));

        when(instrumentRepository.findById(1L))
                .thenReturn(Optional.of(instrument));

        when(performerInstrumentRepository.save(performerInstrument))
                .thenReturn(performerInstrument);

        when(performerInstrumentDtoMapper.mapToDto(performerInstrument))
                .thenReturn(responseDto);

        // Act
        PerformerInstrumentResponseDto result =
                performerInstrumentService.createPerformerInstrument(requestDto);

        // Assert
        assertEquals(1L, result.getId());
        assertEquals(1L, result.getInstrumentId());
        assertEquals(1L, result.getPerformerProfileId());

        verify(performerInstrumentDtoMapper).mapToEntity(requestDto);
        verify(performerInstrumentRepository).existsByPerformerProfileEntityIdAndInstrumentEntityId(
                1L,
                1L
        );
        verify(performerProfileRepository).findById(1L);
        verify(instrumentRepository).findById(1L);
        verify(performerInstrumentRepository).save(performerInstrument);
        verify(performerInstrumentDtoMapper).mapToDto(performerInstrument);
    }

    @Test
    void createPerformerInstrument_shouldThrowDuplicateRecordException_WhenPerformerInstrumentCombinationAlreadyExists() {
        // Arrange
        when(performerInstrumentDtoMapper.mapToEntity(requestDto))
                .thenReturn(performerInstrument);

        when(performerInstrumentRepository.existsByPerformerProfileEntityIdAndInstrumentEntityId(
                1L,
                1L
        ))
                .thenReturn(true);

        // Act + Assert
        DuplicateRecordException exception =
                assertThrows(
                        DuplicateRecordException.class,
                        () -> performerInstrumentService.createPerformerInstrument(requestDto)
                );

        assertEquals(
                "This performer is already linked to this instrument.",
                exception.getMessage()
        );

        verify(performerInstrumentDtoMapper).mapToEntity(requestDto);

        verify(performerInstrumentRepository).existsByPerformerProfileEntityIdAndInstrumentEntityId(
                1L,
                1L
        );
        verify(performerProfileRepository, never())
                .findById(anyLong());

        verify(instrumentRepository, never())
                .findById(anyLong());

        verify(performerInstrumentRepository, never())
                .save(any(PerformerInstrumentEntity.class));

        verify(performerInstrumentDtoMapper, never())
                .mapToDto(any(PerformerInstrumentEntity.class));
    }

    @Test
    void createPerformerInstrument_shouldThrowRecordNotFoundException_WhenPerformerDoesNotExist() {
        // Arrange
        when(performerInstrumentDtoMapper.mapToEntity(requestDto))
                .thenReturn(performerInstrument);

        when(performerInstrumentRepository.existsByPerformerProfileEntityIdAndInstrumentEntityId(
                1L,
                1L
        ))
                .thenReturn(false);

        when(performerProfileRepository.findById(1L))
                .thenReturn(Optional.empty());

        // Act + Assert
        RecordNotFoundException exception =
                assertThrows(
                        RecordNotFoundException.class,
                        () -> performerInstrumentService.createPerformerInstrument(requestDto)
                );

        assertEquals(
                "PerformerProfile with id 1 not found.",
                exception.getMessage()
        );

        verify(performerInstrumentDtoMapper).mapToEntity(requestDto);

        verify(performerInstrumentRepository).existsByPerformerProfileEntityIdAndInstrumentEntityId(
                1L,
                1L
        );

        verify(performerProfileRepository).findById(1L);

        verify(performerInstrumentRepository, never())
                .save(any(PerformerInstrumentEntity.class));

        verify(performerInstrumentDtoMapper, never())
                .mapToDto(any(PerformerInstrumentEntity.class));

    }

    @Test
    void createPerformerInstrument_shouldThrowRecordNotFoundException_WhenInstrumentDoesNotExist() {
        // Arrange
        when(performerProfileRepository.findById(1L))
                .thenReturn(Optional.of(performerProfile));

        when(performerInstrumentDtoMapper.mapToEntity(requestDto))
                .thenReturn(performerInstrument);

        when(performerInstrumentRepository.existsByPerformerProfileEntityIdAndInstrumentEntityId(
                1L,
                1L
        ))
                .thenReturn(false);

        when(instrumentRepository.findById(1L))
                .thenReturn(Optional.empty());

        // Act + Assert
        RecordNotFoundException exception =
                assertThrows(
                        RecordNotFoundException.class,
                        () -> performerInstrumentService.createPerformerInstrument(requestDto)
                );

        assertEquals(
                "Instrument with id 1 not found.",
                exception.getMessage()
        );

        verify(performerInstrumentDtoMapper).mapToEntity(requestDto);

        verify(performerInstrumentRepository).existsByPerformerProfileEntityIdAndInstrumentEntityId(
                1L,
                1L
        );

        verify(performerProfileRepository).findById(1L);

        verify(performerInstrumentRepository, never())
                .save(any(PerformerInstrumentEntity.class));

        verify(performerInstrumentDtoMapper, never())
                .mapToDto(any(PerformerInstrumentEntity.class));


    }

    @Test
    void updatePerformerInstrument_shouldUpdatePerformerInstrument_WhenRequestIsValid() {
        // Arrange
        when(performerInstrumentRepository.findById(1L))
                .thenReturn(Optional.of(performerInstrument));

        when(performerProfileRepository.findById(1L))
                .thenReturn(Optional.of(performerProfile));

        when(instrumentRepository.findById(1L))
                .thenReturn(Optional.of(instrument));

        when(performerInstrumentRepository.save(performerInstrument))
                .thenReturn(performerInstrument);

        when(performerInstrumentDtoMapper.mapToDto(performerInstrument))
                .thenReturn(responseDto);

        // Act
        PerformerInstrumentResponseDto result =
                performerInstrumentService.updatePerformerInstrument(1L, requestDto);

        // Assert
        assertEquals(1L, result.getId());
        assertEquals(1L, result.getInstrumentId());
        assertEquals(1L, result.getPerformerProfileId());

        verify(performerProfileRepository).findById(1L);
        verify(instrumentRepository).findById(1L);
        verify(performerInstrumentRepository).save(performerInstrument);
        verify(performerInstrumentDtoMapper).mapToDto(performerInstrument);
        verify(performerInstrumentRepository).findById(1L);
    }

    @Test
    void updatePerformerInstrument_shouldThrowRecordNotFoundException_WhenPerformerInstrumentDoesNotExist() {
        // Arrange
        when(performerInstrumentRepository.findById(1L))
                .thenReturn(Optional.empty());

        // Act + Assert
        RecordNotFoundException exception =
                assertThrows(
                        RecordNotFoundException.class,
                        () -> performerInstrumentService.updatePerformerInstrument(1L, requestDto)
                );

        assertEquals(
                "PerformerInstrument with id 1 not found.",
                exception.getMessage()
        );

        verify(performerInstrumentRepository).findById(1L);

        verify(performerProfileRepository, never()).findById(anyLong());
        verify(instrumentRepository, never()).findById(anyLong());

        verify(performerInstrumentRepository, never())
                .save(any(PerformerInstrumentEntity.class));

        verify(performerInstrumentDtoMapper, never())
                .mapToDto(any(PerformerInstrumentEntity.class));

    }

    @Test
    void updatePerformerInstrument_shouldThrowRecordNotFoundException_WhenPerformerDoesNotExist() {
        // Arrange
        when(performerInstrumentRepository.findById(1L))
                .thenReturn(Optional.of(performerInstrument));

        when(performerProfileRepository.findById(1L))
                .thenReturn(Optional.empty());

        // Act + Assert
        RecordNotFoundException exception =
                assertThrows(
                        RecordNotFoundException.class,
                        () -> performerInstrumentService.updatePerformerInstrument(1L, requestDto)
                );

        assertEquals(
                "PerformerProfile with id 1 not found.",
                exception.getMessage()
        );

        verify(performerInstrumentRepository).findById(1L);

        verify(performerProfileRepository).findById(1L);
        verify(instrumentRepository, never()).findById(anyLong());

        verify(performerInstrumentRepository, never())
                .save(any(PerformerInstrumentEntity.class));

        verify(performerInstrumentDtoMapper, never())
                .mapToDto(any(PerformerInstrumentEntity.class));
    }

    @Test
    void updatePerformerInstrument_shouldThrowRecordNotFoundException_WhenInstrumentDoesNotExist() {
        // Arrange
        when(performerInstrumentRepository.findById(1L))
                .thenReturn(Optional.of(performerInstrument));

        when(performerProfileRepository.findById(1L))
                .thenReturn(Optional.of(performerProfile));

        when(instrumentRepository.findById(1L))
                .thenReturn(Optional.empty());

        //Act + Assert
        RecordNotFoundException exception =
                assertThrows(
                        RecordNotFoundException.class,
                        () -> performerInstrumentService.updatePerformerInstrument(1L, requestDto)
                );

        assertEquals(
                "Instrument with id 1 not found.",
                exception.getMessage()
        );

        verify(performerInstrumentRepository).findById(1L);

        verify(performerProfileRepository).findById(1L);
        verify(instrumentRepository).findById(1L);

        verify(performerInstrumentRepository, never())
                .save(any(PerformerInstrumentEntity.class));

        verify(performerInstrumentDtoMapper, never())
                .mapToDto(any(PerformerInstrumentEntity.class));
    }

    @Test
    void deletePerformerInstrument_shouldDeletePerformerInstrument_WhenPerformerInstrumentExists() {
        // Arrange
        when(performerInstrumentRepository.findById(1L))
                .thenReturn(Optional.of(performerInstrument));

        // Act
        performerInstrumentService.deletePerformerInstrument(1L);

        // Assert
        verify(performerInstrumentRepository).delete(performerInstrument);


    }
}