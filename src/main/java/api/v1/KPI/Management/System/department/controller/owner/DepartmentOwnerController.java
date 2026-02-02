package api.v1.KPI.Management.System.department.controller.owner;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.department.dto.core.DepartmentCreateDTO;
import api.v1.KPI.Management.System.department.dto.core.DepartmentUpdateDTO;
import api.v1.KPI.Management.System.department.dto.core.DepartmentResponseDTO;
import api.v1.KPI.Management.System.department.service.owner.DepartmentOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/department-admin")
public class DepartmentOwnerController {
    @Autowired
    private DepartmentOwnerService departmentOwnerService;

    @PostMapping("/create")
    public ResponseEntity<DepartmentResponseDTO> create(@RequestBody DepartmentCreateDTO dto,
                                                        @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(departmentOwnerService.ownerCreate(dto,lang));
    }
    @PutMapping("/update")
    public ResponseEntity<AppResponse<String>> update(@RequestBody DepartmentUpdateDTO dto,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(departmentOwnerService.ownerUpdate(dto, lang));
    }
}
