package api.v1.KPI.Management.System.building.controller.admin;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.building.dto.BuildingResponseDTO;
import api.v1.KPI.Management.System.building.dto.admin.BuildingAdminCreateDTO;
import api.v1.KPI.Management.System.building.dto.admin.BuildingAdminUpdateDTO;
import api.v1.KPI.Management.System.building.service.admin.BuildingAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/building")
@PreAuthorize("hasRole('ADMIN')")
public class BuildingAdminController {
    @Autowired
    private BuildingAdminService buildingAdminService;

    @PostMapping("")
    public ResponseEntity<BuildingResponseDTO> createDepartment(@RequestBody BuildingAdminCreateDTO dto) {
        return ResponseEntity.ok().body(buildingAdminService.adminCreate(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BuildingResponseDTO> getDepartment(@PathVariable String id) {
        return ResponseEntity.ok().body(buildingAdminService.adminGetById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<PageImpl<BuildingResponseDTO>> getAllDepartments(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                             @RequestParam(value = "size", defaultValue = "15") int size){
        return ResponseEntity.ok().body(buildingAdminService.adminGetAll(getCurrentPage(page), size));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppResponse<String>> deleteDepartment(@PathVariable String id) {
        return ResponseEntity.ok().body(buildingAdminService.adminDelete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppResponse<String>> updateDepartment(@PathVariable String id,
                                                                @RequestBody BuildingAdminUpdateDTO dto) {
        return ResponseEntity.ok().body(buildingAdminService.adminUpdate(id, dto));
    }

    public static int getCurrentPage(Integer page) {
        return page > 0 ? page - 1 : 1;
    }

}
