package api.v1.KPI.Management.System.building.controller.core;

import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.building.dto.core.BuildingResponseDTO;
import api.v1.KPI.Management.System.building.service.core.BuildingCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/building-core")
public class BuildingCoreController {
    @Autowired
    private BuildingCoreService buildingCoreService;

    @GetMapping("/by-id/{id}")
    public ResponseEntity<BuildingResponseDTO> findById(@PathVariable String id,
                                                        @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(buildingCoreService.getById(id, lang));
    }
    @GetMapping("/by-title/{title}")
    public ResponseEntity<BuildingResponseDTO> findByTitle(@PathVariable String title,
                                                             @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(buildingCoreService.getByTitle(title, lang));
    }
}
