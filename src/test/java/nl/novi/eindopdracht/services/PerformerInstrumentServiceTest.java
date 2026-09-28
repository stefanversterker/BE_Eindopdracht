package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.dtos.performerInstrument.PerformerInstrumentRequestDto;
import nl.novi.eindopdracht.dtos.performerInstrument.PerformerInstrumentResponseDto;
import nl.novi.eindopdracht.entities.InstrumentEntity;
import nl.novi.eindopdracht.entities.PerformerInstrumentEntity;
import nl.novi.eindopdracht.entities.PerformerProfileEntity;
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

}