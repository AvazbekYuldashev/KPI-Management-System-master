package api.v1.KPI.Management.System.category.controller.owner;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.category.dto.core.CategoryResponseDTO;
import api.v1.KPI.Management.System.category.dto.owner.CategoryOwnerCreateDTO;
import api.v1.KPI.Management.System.category.dto.owner.CategoryOwnerUpdateDTO;
import api.v1.KPI.Management.System.category.service.owner.CategoryOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owner/category")
@PreAuthorize("hasRole('OWNER')")
public class CategoryOwnerController {
    @Autowired
    private CategoryOwnerService categoryOwnerService;

    @PostMapping("")
    public ResponseEntity<CategoryResponseDTO> create(@RequestBody CategoryOwnerCreateDTO dto,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") String lang){
        return ResponseEntity.ok().body(categoryOwnerService.createOwner(dto, lang));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppResponse<String>> update(@RequestBody CategoryOwnerUpdateDTO dto,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") String lang){
        return ResponseEntity.ok().body(categoryOwnerService.update(dto, lang));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppResponse<String>> delete(@PathVariable("id") String id,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") String lang){
        return ResponseEntity.ok().body(categoryOwnerService.delete(id, lang));
    }
}
