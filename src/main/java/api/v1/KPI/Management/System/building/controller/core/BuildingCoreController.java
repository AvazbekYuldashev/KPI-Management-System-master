package api.v1.KPI.Management.System.building.controller.core;

import api.v1.KPI.Management.System.building.dto.BuildingResponseDTO;
import api.v1.KPI.Management.System.building.service.core.BuildingCoreService;
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
    private BuildingCoreService buildingCoreService;
    @GetMapping("/{id}")
    public ResponseEntity<BuildingResponseDTO> getById(@PathVariable String id) {
        return ResponseEntity.ok().body(buildingCoreService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<PageImpl<BuildingResponseDTO>> getAlls(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                 @RequestParam(value = "size", defaultValue = "15") int size){
        return ResponseEntity.ok().body(buildingCoreService.getAll(getCurrentPage(page), size));
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<PageImpl<BuildingResponseDTO>> getByDepartment(@PathVariable String id,
                                                                         @RequestParam(value = "page", defaultValue = "1") int page,
                                                                         @RequestParam(value = "size", defaultValue = "15") int size){
        return ResponseEntity.ok().body(buildingCoreService.getByDepartmentId(getCurrentPage(page), size, id));
    }
    public static int getCurrentPage(Integer page) {
        return page > 0 ? page - 1 : 1;
    }

}
