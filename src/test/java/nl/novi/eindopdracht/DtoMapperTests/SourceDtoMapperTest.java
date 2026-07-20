package nl.novi.eindopdracht.DtoMapperTests;

import nl.novi.eindopdracht.dtos.source.SourceResponseDto;
import nl.novi.eindopdracht.entities.PerformerInstrumentEntity;
import nl.novi.eindopdracht.entities.SourceEntity;
import nl.novi.eindopdracht.mappers.SourceDtoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SourceDtoMapperTest {

    private SourceDtoMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new SourceDtoMapper();
    }

    @Test
    void shouldMapEntityToDto() {

        // Arrange

        PerformerInstrumentEntity performerInstrument = new PerformerInstrumentEntity();
        performerInstrument.setId(5L);

        // Act

        SourceEntity entity = new SourceEntity();
        entity.setId(1L);
        entity.setName("Kick");
        entity.setPerformerInstrumentEntity(performerInstrument);

        // Assert

        SourceResponseDto dto = mapper.mapToDto(entity);

        assertEquals(1L, dto.getId());
        assertEquals("Kick", dto.getName());
        assertEquals(5L, dto.getPerformerInstrumentId());
    }

}

