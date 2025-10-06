package api.v1.KPI.Management.System.department.controller.user;

import api.v1.KPI.Management.System.department.dto.DepartmentResponseDTO;
import api.v1.KPI.Management.System.department.service.user.DepartmentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/department")
@PreAuthorize("hasRole('USER')")
public class DepartmentUserController {
    @Autowired
    private DepartmentUserService departmentUserService;

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> getDepartment(@PathVariable String id) {
        return ResponseEntity.ok().body(departmentUserService.userGetById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<PageImpl<DepartmentResponseDTO>> getAllDepartments(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                             @RequestParam(value = "size", defaultValue = "15") int size){
        return ResponseEntity.ok().body(departmentUserService.userGetAll(getCurrentPage(page), size));
    }

    public static int getCurrentPage(Integer page) {
        return page > 0 ? page - 1 : 1;
    }

}