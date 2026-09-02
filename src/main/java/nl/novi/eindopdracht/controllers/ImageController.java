package nl.novi.eindopdracht.controllers;

import jakarta.validation.Valid;
import nl.novi.eindopdracht.dtos.image.ImageRequestDto;
import nl.novi.eindopdracht.dtos.image.ImageResponseDto;
import nl.novi.eindopdracht.helpers.UrlHelper;
import nl.novi.eindopdracht.services.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;
    private final UrlHelper urlHelper;

    public ImageController(
            ImageService imageService,
            UrlHelper urlHelper
    ) {
        this.imageService = imageService;
        this.urlHelper = urlHelper;
    }

    @GetMapping
    public ResponseEntity<List<ImageResponseDto>> getAllImages() {
        List<ImageResponseDto> images = imageService.getAllImages();
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageResponseDto> getImageById(@PathVariable Long id)  {
        ImageResponseDto image = imageService.getImageById(id);
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ImageResponseDto> uploadImage(
            @RequestParam("file") MultipartFile file) {

        ImageResponseDto newImage = imageService.uploadImage(file);

        return ResponseEntity
                .created(urlHelper.getCurrentUrlWithId(newImage.getId()))
                .body(newImage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        imageService.deleteImage(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
