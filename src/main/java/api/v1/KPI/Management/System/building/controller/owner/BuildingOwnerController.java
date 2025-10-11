package api.v1.KPI.Management.System.building.controller.owner;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.building.dto.core.BuildingResponseDTO;
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
    public ResponseEntity<BuildingResponseDTO> create(@RequestBody BuildingOwnerCreateDTO dto,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") String lang){
        return ResponseEntity.ok().body(buildingOwnerService.createOwner(dto, lang));
    }

    @PutMapping("")
    public ResponseEntity<AppResponse<String>> update(@RequestBody BuildingOwnerUpdateDTO dto,
                                                     @RequestHeader(value = "Accept-Language", defaultValue = "UZ") String lang){
        return ResponseEntity.ok().body(buildingOwnerService.update(dto, lang));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppResponse<String>> delete(@PathVariable("id") String id,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") String lang) {
        return ResponseEntity.ok().body(buildingOwnerService.delete(id, lang));
    }

}
