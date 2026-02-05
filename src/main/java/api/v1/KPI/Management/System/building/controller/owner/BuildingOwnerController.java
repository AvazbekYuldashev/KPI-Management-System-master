package api.v1.KPI.Management.System.building.controller.owner;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.building.dto.core.BuildingCreateDTO;
import api.v1.KPI.Management.System.building.dto.core.BuildingResponseDTO;
import api.v1.KPI.Management.System.building.dto.core.BuildingUpdateDTO;
import api.v1.KPI.Management.System.building.service.owner.BuildingOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/building-admin")
public class BuildingOwnerController {
    @Autowired
    private BuildingOwnerService buildingOwnerService;

    @PostMapping("/create")
    public ResponseEntity<BuildingResponseDTO> create(@RequestBody BuildingCreateDTO dto,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(buildingOwnerService.ownerCreate(dto,lang));
    }
    @PutMapping("/update")
    public ResponseEntity<AppResponse<String>> update(@RequestBody BuildingUpdateDTO dto,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(buildingOwnerService.ownerUpdate(dto, lang));
    }

}
