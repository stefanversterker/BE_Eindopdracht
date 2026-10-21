package nl.novi.eindopdracht.config; // Adjust to your actual package

import jakarta.annotation.PostConstruct;
import nl.novi.eindopdracht.repositories.ImageRepository;
import nl.novi.eindopdracht.entities.ImageEntity;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Profile("!prod")
public class ImageDataInitializer {

    private final ImageRepository imageRepository;
    private final ResourceLoader resourceLoader;

    public ImageDataInitializer(
            ImageRepository imageRepository,
            ResourceLoader resourceLoader) {
        this.imageRepository = imageRepository;
        this.resourceLoader = resourceLoader;
    }

    @PostConstruct
    public void loadImage() {
        String fileName = "image.jpg";

        if (imageRepository.existsByFileName(fileName)) {
            return;
        }

        try {
            Resource resource = resourceLoader.getResource(
                    "classpath:static/images/image.jpg"
            );

            if (!resource.exists()) {
                throw new IllegalStateException(
                        "Image file not found: " + fileName
                );
            }

            ImageEntity image = new ImageEntity();
            image.setFileName(fileName);
            image.setContentType("image/jpeg");

            image.setContents(resource.getContentAsByteArray());

            imageRepository.save(image);

        } catch (IOException e) {
            throw new IllegalStateException(
                    "Could not load image: " + fileName, e
            );
        }
    }
}