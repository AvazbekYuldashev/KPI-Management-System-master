package api.v1.KPI.Management.System.department.controller.admin;

import api.v1.KPI.Management.System.department.dto.DepartmentResponseDTO;
import api.v1.KPI.Management.System.department.dto.admin.DepartmentAdminCreateDTO;
import api.v1.KPI.Management.System.department.service.admin.DepartmentAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/department")
@PreAuthorize("hasRole('ADMIN')")
public class DepartmentAdminController {
    @Autowired
    private DepartmentAdminService departmentAdminService;

    @PostMapping("")
    public ResponseEntity<DepartmentResponseDTO> createDepartment(@RequestBody DepartmentAdminCreateDTO dto) {
        return ResponseEntity.ok().body(departmentAdminService.create(dto));
    }
}
