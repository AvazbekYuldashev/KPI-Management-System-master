package api.v1.KPI.Management.System.department.controller.admin;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.department.dto.DepartmentResponseDTO;
import api.v1.KPI.Management.System.department.dto.admin.DepartmentAdminCreateDTO;
import api.v1.KPI.Management.System.department.dto.admin.DepartmentAdminUpdateDTO;
import api.v1.KPI.Management.System.department.service.admin.DepartmentAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin/department")
@PreAuthorize("hasRole('ADMIN')")
public class DepartmentAdminController {
    @Autowired
    private DepartmentAdminService departmentAdminService;

    @PutMapping("/{id}")
    public ResponseEntity<AppResponse<String>> updateDepartment(@PathVariable String id,
                                                                @RequestBody DepartmentAdminUpdateDTO dto) {
        return ResponseEntity.ok().body(departmentAdminService.adminUpdate(id, dto));
    }


}
