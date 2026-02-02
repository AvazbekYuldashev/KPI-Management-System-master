package api.v1.KPI.Management.System.category.controller.owner;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.category.dto.core.CategoryResponseDTO;
import api.v1.KPI.Management.System.category.dto.owner.CategoryCreateDTO;
import api.v1.KPI.Management.System.category.dto.owner.CategoryUpdateDTO;
import api.v1.KPI.Management.System.category.service.owner.CategoryOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category-admin")
public class CategoryOwnerController {
    @Autowired
    private CategoryOwnerService categoryOwnerService;

    @PostMapping("/create")
    public ResponseEntity<CategoryResponseDTO> create(@RequestBody CategoryCreateDTO dto,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(categoryOwnerService.ownerCreate(dto,lang));
    }
    @PutMapping("/update")
    public ResponseEntity<AppResponse<String>> update(@RequestBody CategoryUpdateDTO dto,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(categoryOwnerService.ownerUpdate(dto, lang));
    }
}
