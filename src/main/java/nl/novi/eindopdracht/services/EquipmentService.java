package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.dtos.event.EventResponseDto;
import nl.novi.eindopdracht.entities.EquipmentEntity;
import nl.novi.eindopdracht.entities.ImageEntity;
import nl.novi.eindopdracht.exceptions.RecordNotFoundException;
import nl.novi.eindopdracht.repositories.EquipmentRepository;
import nl.novi.eindopdracht.repositories.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final ImageRepository imageRepository;

    
    public EquipmentService(EquipmentRepository equipmentRepository, ImageRepository imageRepository) {
        this.equipmentRepository = equipmentRepository;
        this.imageRepository = imageRepository;
    }

    @Transactional
    public void assignImage(Long equipmentId, Long imageId) {

        EquipmentEntity equipment = getEquipmentEntity(equipmentId);
        ImageEntity image = getImageEntity(imageId);

        equipment.setImage(image);
    }

    @Transactional
    public void removeImage(Long equipmentId) {
        EquipmentEntity equipment = getEquipmentEntity(equipmentId);
        equipment.setImage(null);
    }

    // Helpers
    private EquipmentEntity getEquipmentEntity(Long id) {
        return equipmentRepository.findById(id)
                .orElseThrow(() ->
                        new RecordNotFoundException("Equipment with id " + id + " not found."));

    }

    private ImageEntity getImageEntity(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(() ->
                        new RecordNotFoundException("Image with id " + id + " not found."));

    }
    
}
