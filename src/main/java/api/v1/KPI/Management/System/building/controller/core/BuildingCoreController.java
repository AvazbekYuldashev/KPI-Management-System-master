package api.v1.KPI.Management.System.building.controller.core;

import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.building.dto.BuildingResponseDTO;
import api.v1.KPI.Management.System.building.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/core/building")
@PreAuthorize("hasAnyRole('OWNER','ADMIN', 'MANAGER', 'EMPLOYEE', 'USER')")
public class BuildingCoreController {
    @Autowired
    private BuildingService buildingService;
    @GetMapping("/{id}")
    public ResponseEntity<BuildingResponseDTO> getById(@PathVariable String id) {
        return ResponseEntity.ok().body(buildingService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<PageImpl<BuildingResponseDTO>> getAlls(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                 @RequestParam(value = "size", defaultValue = "15") int size){
        return ResponseEntity.ok().body(buildingService.getAll(getCurrentPage(page), size));
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<PageImpl<BuildingResponseDTO>> getByDepartment(@PathVariable String id,
                                                                         @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang,
                                                                         @RequestParam(value = "page", defaultValue = "1") int page,
                                                                         @RequestParam(value = "size", defaultValue = "15") int size){
        return ResponseEntity.ok().body(buildingService.getByDepartmentId(getCurrentPage(page), size, id, lang));
    }
    public static int getCurrentPage(Integer page) {
        return page > 0 ? page - 1 : 1;
    }

}
