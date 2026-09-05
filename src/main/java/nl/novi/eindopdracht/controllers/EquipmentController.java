package nl.novi.eindopdracht.controllers;

import nl.novi.eindopdracht.services.EquipmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    public EquipmentController(
            EquipmentService equipmentService
    ) {
        this.equipmentService = equipmentService;
    }

    @PutMapping("/{equipmentId}/image/{imageId}")
    public ResponseEntity<Void> assignImage(
            @PathVariable Long equipmentId,
            @PathVariable Long imageId) {

        equipmentService.assignImage(equipmentId, imageId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{equipmentId}/image")
    public ResponseEntity<Void> removeImage(
            @PathVariable Long equipmentId) {

        equipmentService.removeImage(equipmentId);

        return ResponseEntity.noContent().build();
    }

}
