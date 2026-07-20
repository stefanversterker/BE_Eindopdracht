package nl.novi.eindopdracht.DtoMapperTests;

import nl.novi.eindopdracht.dtos.act.ActRequestDto;
import nl.novi.eindopdracht.dtos.act.ActResponseDto;
import nl.novi.eindopdracht.entities.ActEntity;
import nl.novi.eindopdracht.mappers.ActDtoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActDtoMapperTest {

    private ActDtoMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ActDtoMapper();
    }

    @Test
    void shouldMapEntityToDto() {

        // Arrange

        ActEntity entity = new ActEntity();
        entity.setId(1L);
        entity.setName("Rita Lynn");
        entity.setPhone("06 47523933");
        entity.setEmail("info@ritalynn.zu");

        // Act

        ActResponseDto dto = mapper.mapToDto(entity);

        // Assert

        assertEquals(1L, dto.getId());
        assertEquals("Rita Lynn", dto.getName());
        assertEquals("06 47523933", dto.getPhone());
        assertEquals("info@ritalynn.zu", dto.getEmail());
    }

    @Test
    void shouldMapRequestDtoToEntity() {

        // Arrange

        ActRequestDto requestDto = new ActRequestDto();
        requestDto.setName("Rita Lynn");
        requestDto.setPhone("06 47523933");
        requestDto.setEmail("info@ritalynn.zu");

        // Act

        ActEntity entity = mapper.mapToEntity(requestDto);

        // Assert

        assertEquals("Rita Lynn", entity.getName());
        assertEquals("06 47523933", entity.getPhone());
        assertEquals("info@ritalynn.zu", entity.getEmail());

    }

    @Test
    void shouldMapEntityListToDtoList() {

        // Arrange

        ActEntity entity1 = new ActEntity();
        entity1.setId(1L);
        entity1.setName("entity1");
        entity1.setPhone("06 11111111");
        entity1.setEmail("info@entity1.com");

        ActEntity entity2 = new ActEntity();
        entity2.setId(2L);
        entity2.setName("entity2");
        entity2.setPhone("06 22222222");
        entity2.setEmail("info@entity2.com");

        ActEntity entity3 = new ActEntity();
        entity3.setId(3L);
        entity3.setName("entity3");
        entity3.setPhone("06 33333333");
        entity3.setEmail("info@entity3.com");

        List<ActEntity> entities = List.of(entity1, entity2, entity3);

        // Act

        List<ActResponseDto> dtoList = mapper.mapToDto(entities);

        // Assert

        assertEquals(3, dtoList.size());

        assertEquals("entity1",dtoList.get(0).getName());
        assertEquals("entity2",dtoList.get(1).getName());
        assertEquals("entity3",dtoList.get(2).getName());

        assertEquals("06 11111111",dtoList.get(0).getPhone());
        assertEquals("06 22222222",dtoList.get(1).getPhone());
        assertEquals("06 33333333",dtoList.get(2).getPhone());

        assertEquals("info@entity1.com", dtoList.get(0).getEmail());
        assertEquals("info@entity2.com", dtoList.get(1).getEmail());
        assertEquals("info@entity3.com", dtoList.get(2).getEmail());

    }

}
