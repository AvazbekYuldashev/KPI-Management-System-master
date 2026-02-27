package api.v1.KPI.Management.System.category.controller.manager;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.category.dto.core.CategoryResponseDTO;
import api.v1.KPI.Management.System.category.dto.manager.CategoryManagerCreateDTO;
import api.v1.KPI.Management.System.category.dto.manager.CategoryManagerUpdateDTO;
import api.v1.KPI.Management.System.category.service.manager.CategoryManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category-manager")
public class CategoryManagerController {
    @Autowired
    private CategoryManagerService categoryManagerService;

    @PostMapping("/create")
    public ResponseEntity<CategoryResponseDTO> create(@RequestBody CategoryManagerCreateDTO dto,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(categoryManagerService.managerCreate(dto,lang));
    }
    @PutMapping("/update")
    public ResponseEntity<AppResponse<String>> update(@RequestBody CategoryManagerUpdateDTO dto,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(categoryManagerService.managerUpdate(dto, lang));
    }
    public static int getCurrentPage(Integer page) {
        return (page != null && page > 0) ? page - 1 : 0;
    }
}
