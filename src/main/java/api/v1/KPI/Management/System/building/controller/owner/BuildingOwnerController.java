package api.v1.KPI.Management.System.building.controller.owner;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.building.dto.BuildingResponseDTO;
import api.v1.KPI.Management.System.building.dto.admin.BuildingAdminCreateDTO;
import api.v1.KPI.Management.System.building.dto.admin.BuildingAdminUpdateDTO;
import api.v1.KPI.Management.System.building.dto.owner.BuildingOwnerCreateDTO;
import api.v1.KPI.Management.System.building.dto.owner.BuildingOwnerUpdateDTO;
import api.v1.KPI.Management.System.building.service.owner.BuildingOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owner/building")
@PreAuthorize("hasRole('OWNER')")
public class BuildingOwnerController {
    @Autowired
    private BuildingOwnerService buildingOwnerService;

    @PostMapping("")
    public ResponseEntity<BuildingResponseDTO> create(@RequestBody BuildingOwnerCreateDTO dto) {
        return ResponseEntity.ok().body(buildingOwnerService.ownerCreate(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppResponse<String>> delete(@PathVariable String id) {
        return ResponseEntity.ok().body(buildingOwnerService.ownerDelete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppResponse<String>> update(@PathVariable String id,
                                                      @RequestBody BuildingOwnerUpdateDTO dto) {
        return ResponseEntity.ok().body(buildingOwnerService.ownerUpdate(id, dto));
    }
}
