package nl.novi.eindopdracht.mappers;

import nl.novi.eindopdracht.dtos.image.ImageResponseDto;
import nl.novi.eindopdracht.dtos.image.ImageRequestDto;
import nl.novi.eindopdracht.entities.ImageEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ImageDtoMapper implements DtoMapper<ImageResponseDto, ImageRequestDto, ImageEntity> {

    @Override
    public ImageResponseDto mapToDto(ImageEntity entity) {
        var result = new ImageResponseDto();
        result.setId(entity.getId());
        result.setFileName(entity.getFileName());
        result.setContentType(entity.getContentType());
        return result;
    }

    @Override
    public List<ImageResponseDto> mapToDto(List<ImageEntity> entities) {
        return entities.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public ImageEntity mapToEntity(ImageRequestDto requestDto) {
        // Image uploads are handled through MultipartFile in ImageService.
        return new ImageEntity();
    }
}
