package api.v1.KPI.Management.System.building.controller.admin;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.building.dto.admin.BuildingAdminCreateDTO;
import api.v1.KPI.Management.System.building.dto.admin.BuildingAdminUpdateDTO;
import api.v1.KPI.Management.System.building.dto.core.BuildingResponseDTO;
import api.v1.KPI.Management.System.building.service.admin.BuildingAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/building")
@PreAuthorize("hasRole('ADMIN')")
public class BuildingAdminController {
    @Autowired
    private BuildingAdminService departmentAdminService;

    @PostMapping("")
    public ResponseEntity<BuildingResponseDTO> create(@RequestBody BuildingAdminCreateDTO dto,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") String lang){
        return ResponseEntity.ok().body(departmentAdminService.createAdmin(dto, lang));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppResponse<String>> update(@RequestBody BuildingAdminUpdateDTO dto,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") String lang){
        return ResponseEntity.ok().body(departmentAdminService.update(dto, lang));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppResponse<String>> delete(@PathVariable("id") String id,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") String lang) {
        return ResponseEntity.ok().body(departmentAdminService.delete(id, lang));
    }
}
