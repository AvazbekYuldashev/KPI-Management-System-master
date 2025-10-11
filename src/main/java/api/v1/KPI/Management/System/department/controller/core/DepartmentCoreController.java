package api.v1.KPI.Management.System.department.controller.core;

import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.department.dto.core.DepartmentResponseDTO;
import api.v1.KPI.Management.System.department.service.core.DepartmentCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/core/department")
@PreAuthorize("hasAnyRole('OWNER','ADMIN', 'MANAGER', 'EMPLOYEE', 'USER')")
public class DepartmentCoreController {
    @Autowired
    private DepartmentCoreService departmentCoreService;

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> getById(@PathVariable String id,
                                                         @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(departmentCoreService.getById(id, lang));
    }

}
