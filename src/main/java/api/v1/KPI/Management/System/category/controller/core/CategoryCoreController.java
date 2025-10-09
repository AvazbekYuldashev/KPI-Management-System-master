package api.v1.KPI.Management.System.category.controller.core;

import api.v1.KPI.Management.System.category.dto.CategoryResponseDTO;
import api.v1.KPI.Management.System.category.service.core.CategoryCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/category")
@PreAuthorize("hasRole('USER')")
public class CategoryCoreController {
    @Autowired
    private CategoryCoreService categoryCoreService;

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getDepartment(@PathVariable String id) {
        return ResponseEntity.ok().body(categoryCoreService.userGetById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<PageImpl<CategoryResponseDTO>> getAllDepartments(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                           @RequestParam(value = "size", defaultValue = "15") int size){
        return ResponseEntity.ok().body(categoryCoreService.userGetAll(getCurrentPage(page), size));
    }

    public static int getCurrentPage(Integer page) {
        return page > 0 ? page - 1 : 1;
    }
}
