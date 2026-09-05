package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.dtos.image.ImageResponseDto;
import nl.novi.eindopdracht.entities.ImageEntity;
import nl.novi.eindopdracht.exceptions.RecordNotFoundException;
import nl.novi.eindopdracht.mappers.ImageDtoMapper;
import nl.novi.eindopdracht.repositories.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ImageService {

    private final ImageRepository imageRepository;
    private final ImageDtoMapper imageDtoMapper;


    public ImageService(ImageRepository imageRepository, ImageDtoMapper imageDtoMapper) {
        this.imageRepository = imageRepository;
        this.imageDtoMapper = imageDtoMapper;
    }

    @Transactional(readOnly = true)
    public List<ImageResponseDto> getAllImages() {
        return imageDtoMapper.mapToDto(imageRepository.findAll());
    }

    @Transactional(readOnly = true)
    public ImageResponseDto getImageById(long id) {
        return imageDtoMapper.mapToDto(getImageEntity(id));
    }

    public ImageResponseDto uploadImage(MultipartFile file) {
        ImageEntity imageEntity = new ImageEntity();

        imageEntity.setFileName(file.getOriginalFilename());
        imageEntity.setContentType(file.getContentType());

        try {
            imageEntity.setContents(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to read uploaded file", e);
        }

        imageEntity = imageRepository.save(imageEntity);

        return imageDtoMapper.mapToDto(imageEntity);
    }

    @Transactional(readOnly = true)
    public ImageEntity downloadImage(Long id) {
        return getImageEntity(id);
    }

    public void deleteImage(Long id) {
        ImageEntity image = getImageEntity(id);
        imageRepository.delete(image);
    }

    // Helpers
    private ImageEntity getImageEntity(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(() ->
                        new RecordNotFoundException("Image " + id + " not found."));
    }
    
}
