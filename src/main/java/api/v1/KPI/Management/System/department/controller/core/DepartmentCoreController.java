package api.v1.KPI.Management.System.department.controller.core;

import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.department.dto.core.DepartmentResponseDTO;
import api.v1.KPI.Management.System.department.service.core.DepartmentCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/department-core")
public class DepartmentCoreController {
    @Autowired
    private DepartmentCoreService departmentCoreService;

    @GetMapping("/by-id/{id}")
    public ResponseEntity<DepartmentResponseDTO> findById(@PathVariable String id,
                                                          @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(departmentCoreService.getById(id, lang));
    }
    @GetMapping("/by-title/{title}")
    public ResponseEntity<DepartmentResponseDTO> findByTitle(@PathVariable String title,
                                                          @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(departmentCoreService.getByTitle(title, lang));
    }

    public static int getCurrentPage(Integer page) {
        return (page != null && page > 0) ? page - 1 : 0;
    }
}
