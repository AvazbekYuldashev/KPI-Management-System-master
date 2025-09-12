package api.v1.KPI.Management.System.category.controller.user;


import api.v1.KPI.Management.System.category.dto.CategoryResponseDTO;
import api.v1.KPI.Management.System.category.service.user.CategoryUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/category")
@PreAuthorize("hasRole('USER')")
public class CategoryUserController {
    @Autowired
    private CategoryUserService categoryUserService;

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getDepartment(@PathVariable String id) {
        return ResponseEntity.ok().body(categoryUserService.userGetById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<PageImpl<CategoryResponseDTO>> getAllDepartments(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                           @RequestParam(value = "size", defaultValue = "15") int size){
        return ResponseEntity.ok().body(categoryUserService.userGetAll(getCurrentPage(page), size));
    }

    public static int getCurrentPage(Integer page) {
        return page > 0 ? page - 1 : 1;
    }
}
