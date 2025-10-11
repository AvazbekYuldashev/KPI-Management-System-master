package api.v1.KPI.Management.System.building.controller.core;


import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.building.dto.core.BuildingResponseDTO;
import api.v1.KPI.Management.System.building.service.core.BuildingCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/core/department")
@PreAuthorize("hasAnyRole('OWNER','ADMIN', 'MANAGER', 'EMPLOYEE', 'USER')")
public class BuildingCoreController {
    @Autowired
    private BuildingCoreService buildingCoreService;

    @GetMapping("/{id}")
    public ResponseEntity<BuildingResponseDTO> getById(@PathVariable String id,
                                                       @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(buildingCoreService.getById(id, lang));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<BuildingResponseDTO>> getAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                              @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                              @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(buildingCoreService.getAll(getCurrentPage(page), size, lang));
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<Page<BuildingResponseDTO>> getByDepartmentId(@PathVariable String id,
                                                                                @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                                                @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                                                @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(buildingCoreService.getByDepartmentId(id, getCurrentPage(page), size, lang));
    }

    public static int getCurrentPage(Integer page) {
        return page > 0 ? page - 1 : 1;
    }
}
