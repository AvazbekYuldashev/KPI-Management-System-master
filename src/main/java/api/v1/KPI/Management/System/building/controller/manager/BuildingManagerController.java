package api.v1.KPI.Management.System.building.controller.manager;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.building.dto.core.BuildingResponseDTO;
import api.v1.KPI.Management.System.building.dto.manager.BuildingManagerCreateDTO;
import api.v1.KPI.Management.System.building.dto.manager.BuildingManagerUpdateDTO;
import api.v1.KPI.Management.System.building.service.manager.BuildingManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/building-manager")
public class BuildingManagerController {
    @Autowired
    private BuildingManagerService buildingManagerService;


    @PostMapping("/create")
    public ResponseEntity<BuildingResponseDTO> create(@RequestBody BuildingManagerCreateDTO dto,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(buildingManagerService.managerCreate(dto,lang));
    }

    @PutMapping("/update")
    public ResponseEntity<AppResponse<String>> update(@RequestBody BuildingManagerUpdateDTO dto,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(buildingManagerService.managerUpdate(dto, lang));
    }
    @GetMapping("/all")
    public ResponseEntity<Page<BuildingResponseDTO>> findAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                             @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(buildingManagerService.getAllPage(getCurrentPage(page), size, lang));
    }


    public static int getCurrentPage(Integer page) {
        return (page != null && page > 0) ? page - 1 : 0;
    }

}
