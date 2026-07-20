package nl.novi.eindopdracht.mappers;

import nl.novi.eindopdracht.entities.BaseEntity;

import java.util.List;

public interface DtoMapper<
        RESPONSE,
        REQUEST ,
        ENTITY extends BaseEntity> {

    RESPONSE mapToDto(ENTITY entity);

    List<RESPONSE> mapToDto(List<ENTITY> entities);

    ENTITY mapToEntity(REQUEST dto);
}
