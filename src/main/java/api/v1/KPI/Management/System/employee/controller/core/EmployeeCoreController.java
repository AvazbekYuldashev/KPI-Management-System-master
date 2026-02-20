package api.v1.KPI.Management.System.employee.controller.core;


import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.employee.dto.EmployeeResponseDTO;
import api.v1.KPI.Management.System.employee.service.core.EmployeeCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee-core")
public class EmployeeCoreController {
    @Autowired
    private EmployeeCoreService employeeCoreService;

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getById(@PathVariable String id,
                                                       @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(employeeCoreService.getById(id, lang));
    }

    @GetMapping("/by-department/{id}")
    public ResponseEntity<Page<EmployeeResponseDTO>> getByDepartmentId(@PathVariable String id,
                                                                       @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                                       @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(employeeCoreService.findByDepartmentId(id, page, size, lang));
    }

}
