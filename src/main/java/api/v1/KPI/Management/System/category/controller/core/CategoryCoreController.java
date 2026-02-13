package api.v1.KPI.Management.System.category.controller.core;


import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.category.dto.core.CategoryResponseDTO;
import api.v1.KPI.Management.System.category.service.core.CategoryCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category-core")
public class CategoryCoreController {
    @Autowired
    private CategoryCoreService categoryCoreService;

    @GetMapping("/by-id/{id}")
    public ResponseEntity<CategoryResponseDTO> findById(@PathVariable String id,
                                                        @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(categoryCoreService.getById(id, lang));
    }

    @GetMapping("/by-title/{title}")
    public ResponseEntity<CategoryResponseDTO> findByTitle(@PathVariable String title,
                                                           @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(categoryCoreService.getByTitle(title, lang));
    }

    @GetMapping("/by-department-id/{id}")
    public ResponseEntity<Page<CategoryResponseDTO>> findByDepartmentId(@PathVariable String id,
                                                                        @RequestParam(value = "page", defaultValue = "1") int page,
                                                                        @RequestParam(value = "size", defaultValue = "30") int size,
                                                                        @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(categoryCoreService.getByDepartmentId(id, getCurrentPage(page), size, lang));
    }

    @GetMapping("/by-building-id/{id}")
    public ResponseEntity<Page<CategoryResponseDTO>> findByBuildingId(@PathVariable String id,
                                                                      @RequestParam(value = "page", defaultValue = "1") int page,
                                                                      @RequestParam(value = "size", defaultValue = "30") int size,
                                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(categoryCoreService.getByBuildingId(id, getCurrentPage(page), size, lang));
    }

    public static int getCurrentPage(Integer page) {
        return (page != null && page > 0) ? page - 1 : 0;
    }}
