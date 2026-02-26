package api.v1.KPI.Management.System.building.controller.admin;


import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.building.dto.admin.BuildingAdminUpdateDTO;
import api.v1.KPI.Management.System.building.service.admin.BuildingAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/building-admin")
public class BuildingAdminController {
    @Autowired
    private BuildingAdminService buildingAdminService;

    @PutMapping("/update")
    public ResponseEntity<AppResponse<String>> update(@RequestBody BuildingAdminUpdateDTO dto,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(buildingAdminService.adminUpdate(dto, lang));
    }
}
