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
    public ResponseEntity<BuildingResponseDTO> create(@RequestBody BuildingAdminCreateDTO dto) {
        return ResponseEntity.ok().body(buildingAdminService.adminCreate(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppResponse<String>> delete(@PathVariable String id) {
        return ResponseEntity.ok().body(buildingAdminService.adminDelete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppResponse<String>> update(@PathVariable String id,
                                                                @RequestBody BuildingAdminUpdateDTO dto) {
        return ResponseEntity.ok().body(buildingAdminService.adminUpdate(id, dto));
    }

}
