package api.v1.KPI.Management.System.department.controller.admin;

import api.v1.KPI.Management.System.department.dto.DepartmentResponseDTO;
import api.v1.KPI.Management.System.department.dto.admin.DepartmentAdminCreateDTO;
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

    @PostMapping("")
    public ResponseEntity<DepartmentResponseDTO> createDepartment(@RequestBody DepartmentAdminCreateDTO dto) {
        return ResponseEntity.ok().body(departmentAdminService.adminCreate(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> getDepartment(@PathVariable String id) {
        return ResponseEntity.ok().body(departmentAdminService.getById(id));
    }

    @GetMapping("")
    public ResponseEntity<PageImpl<DepartmentResponseDTO>> getAllDepartments(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                             @RequestParam(value = "size", defaultValue = "15") int size){
        return ResponseEntity.ok().body(departmentAdminService.getAll(getCurrentPage(page), size));
    }

    public static int getCurrentPage(Integer page) {
        return page > 0 ? page - 1 : 1;
    }

}
