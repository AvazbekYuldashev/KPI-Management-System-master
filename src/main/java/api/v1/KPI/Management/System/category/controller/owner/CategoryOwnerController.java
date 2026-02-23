package api.v1.KPI.Management.System.category.controller.owner;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.category.dto.core.CategoryResponseDTO;
import api.v1.KPI.Management.System.category.dto.owner.CategoryOwnerCreateDTO;
import api.v1.KPI.Management.System.category.dto.owner.CategoryOwnerUpdateDTO;
import api.v1.KPI.Management.System.category.service.owner.CategoryOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category-owner")
public class CategoryOwnerController {
    @Autowired
    private CategoryOwnerService categoryOwnerService;

    @PostMapping("/create")
    public ResponseEntity<CategoryResponseDTO> create(@RequestBody CategoryOwnerCreateDTO dto,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(categoryOwnerService.ownerCreate(dto,lang));
    }
    @PutMapping("/update")
    public ResponseEntity<AppResponse<String>> update(@RequestBody CategoryOwnerUpdateDTO dto,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(categoryOwnerService.ownerUpdate(dto, lang));
    }
    @GetMapping("/all")
    public ResponseEntity<Page<CategoryResponseDTO>> findAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                             @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(categoryOwnerService.getAllPage(getCurrentPage(page), size, lang));
    }
    public static int getCurrentPage(Integer page) {
        return (page != null && page > 0) ? page - 1 : 0;
    }

}
