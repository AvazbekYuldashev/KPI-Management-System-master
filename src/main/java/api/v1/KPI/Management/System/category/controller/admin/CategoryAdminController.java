package api.v1.KPI.Management.System.category.controller.admin;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.category.dto.CategoryResponseDTO;
import api.v1.KPI.Management.System.category.dto.admin.CategoryAdminUpdateDTO;
import api.v1.KPI.Management.System.category.dto.admin.CategoryAdminCreateDTO;
import api.v1.KPI.Management.System.category.service.admin.CategoryAdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/category")
@PreAuthorize("hasRole('ADMIN')")
public class CategoryAdminController {
    @Autowired
    private CategoryAdminService categoryAdminService;

    @PostMapping("")
    public ResponseEntity<CategoryResponseDTO> createDepartment(@RequestBody CategoryAdminCreateDTO dto) {
        return ResponseEntity.ok().body(categoryAdminService.adminCreate(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getDepartment(@PathVariable String id) {
        return ResponseEntity.ok().body(categoryAdminService.adminGetById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<PageImpl<CategoryResponseDTO>> getAllDepartments(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                           @RequestParam(value = "size", defaultValue = "15") int size){
        return ResponseEntity.ok().body(categoryAdminService.adminGetAll(getCurrentPage(page), size));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppResponse<String>> deleteDepartment(@PathVariable String id) {
        return ResponseEntity.ok().body(categoryAdminService.adminDelete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppResponse<String>> updateDepartment(@PathVariable String id,
                                                                @RequestBody CategoryAdminUpdateDTO dto) {
        return ResponseEntity.ok().body(categoryAdminService.adminUpdate(id, dto));
    }

    public static int getCurrentPage(Integer page) {
        return page > 0 ? page - 1 : 1;
    }




}
