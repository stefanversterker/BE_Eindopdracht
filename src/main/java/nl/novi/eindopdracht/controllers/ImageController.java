package nl.novi.eindopdracht.controllers;

import nl.novi.eindopdracht.dtos.image.ImageResponseDto;
import nl.novi.eindopdracht.entities.ImageEntity;
import nl.novi.eindopdracht.helpers.UrlHelper;
import nl.novi.eindopdracht.services.ImageService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> downloadImage(@PathVariable Long id) {

        ImageEntity image = imageService.downloadImage(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + image.getFileName() + "\"")
                .contentType(MediaType.parseMediaType(image.getContentType()))
                .body(image.getContents());
    }

    @PostMapping
    public ResponseEntity<ImageResponseDto> uploadImage(

            // Look in the multipart form data for a field named "file" and put it into this parameter.
            @RequestParam("file") MultipartFile file) {

        // Pass uploaded file to service
        ImageResponseDto newImage = imageService.uploadImage(file);

        // Return id, fileName and contentType in JSON format. Do not return actual image bytes.
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
