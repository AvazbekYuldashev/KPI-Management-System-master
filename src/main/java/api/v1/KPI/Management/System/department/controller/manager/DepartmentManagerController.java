package api.v1.KPI.Management.System.department.controller.manager;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.department.dto.core.DepartmentResponseDTO;
import api.v1.KPI.Management.System.department.dto.manager.DepartmentManagerUpdateDTO;
import api.v1.KPI.Management.System.department.service.manager.DepartmentManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/department-manager")
public class DepartmentManagerController {
    @Autowired
    private DepartmentManagerService departmentManagerService;

    @GetMapping("/all")
    public ResponseEntity<DepartmentResponseDTO> myDepartment(@RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(departmentManagerService.getMyDepartment(lang));
    }

    @PutMapping("/update")
    public ResponseEntity<AppResponse<String>> myDepartment(@RequestBody DepartmentManagerUpdateDTO dto,
                                                            @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(departmentManagerService.changeDetail(dto, lang));
    }

    public static int getCurrentPage(Integer page) {
        return (page != null && page > 0) ? page - 1 : 0;
    }
}
