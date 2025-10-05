package api.v1.KPI.Management.System.department.controller.owner;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.department.dto.DepartmentResponseDTO;
import api.v1.KPI.Management.System.department.dto.owner.DepartmentOwnerCreateDTO;
import api.v1.KPI.Management.System.department.dto.owner.DepartmentOwnerUpdateDTO;
import api.v1.KPI.Management.System.department.service.owner.DepartmentOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/owner/department")
@PreAuthorize("hasRole('OWNER')")
public class DepartmentOwnerController {
    @Autowired
    private DepartmentOwnerService departmentOwnerService;

    @PostMapping("")
    public ResponseEntity<DepartmentResponseDTO> createDepartment(@RequestBody DepartmentOwnerCreateDTO dto) {
        return ResponseEntity.ok().body(departmentOwnerService.ownerCreate(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> getDepartment(@PathVariable String id) {
        return ResponseEntity.ok().body(departmentOwnerService.ownerGetById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<PageImpl<DepartmentResponseDTO>> getAllDepartments(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                             @RequestParam(value = "size", defaultValue = "15") int size){
        return ResponseEntity.ok().body(departmentOwnerService.ownerGetAll(getCurrentPage(page), size));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppResponse<String>> deleteDepartment(@PathVariable String id) {
        return ResponseEntity.ok().body(departmentOwnerService.ownerDelete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppResponse<String>> updateDepartment(@PathVariable String id,
                                                                @RequestBody DepartmentOwnerUpdateDTO dto) {
        return ResponseEntity.ok().body(departmentOwnerService.ownerUpdate(id, dto));
    }

    public static int getCurrentPage(Integer page) {
        return page > 0 ? page - 1 : 1;
    }

}
