package api.v1.KPI.Management.System.category.controller.admin;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.category.dto.admin.CategoryAdminUpdateDTO;
import api.v1.KPI.Management.System.category.service.admin.CategoryAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/category")
@PreAuthorize("hasRole('ADMIN')")
public class CategoryAdminController {
    @Autowired
    private CategoryAdminService categoryAdminService;

    @PutMapping("/{id}")
    public ResponseEntity<AppResponse<String>> update(@RequestBody CategoryAdminUpdateDTO dto,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") String lang){
        return ResponseEntity.ok().body(categoryAdminService.update(dto, lang));
    }
}
