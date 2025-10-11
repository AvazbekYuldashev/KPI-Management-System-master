package api.v1.KPI.Management.System.department.controller.admin;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.department.dto.admin.DepartmentAdminUpdateDTO;
import api.v1.KPI.Management.System.department.service.admin.DepartmentAdminService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<AppResponse<String>> update(@RequestBody DepartmentAdminUpdateDTO dto,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") String lang){
        return ResponseEntity.ok().body(departmentAdminService.update(dto, lang));
    }
}
