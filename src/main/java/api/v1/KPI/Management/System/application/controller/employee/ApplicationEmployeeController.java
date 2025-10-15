package api.v1.KPI.Management.System.application.controller.employee;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.application.dto.core.ApplicationResponseDTO;
import api.v1.KPI.Management.System.application.service.employee.ApplicationEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee/application")
@PreAuthorize("hasRole('EMPLOYEE')")
public class ApplicationEmployeeController {
    @Autowired
    private ApplicationEmployeeService applicationEmployeeService;

    @GetMapping("/all")
    public ResponseEntity<Page<ApplicationResponseDTO>> getAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                               @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                               @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(applicationEmployeeService.getAll(getCurrentPage(page), size, lang));
    }

    @PatchMapping("/approve/{id}")
    public ResponseEntity<AppResponse<String>> approve(@PathVariable String id,
                                                       @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(applicationEmployeeService.approve(id, lang));
    }

    @PatchMapping("/review/{id}")
    public ResponseEntity<AppResponse<String>> review(@PathVariable String id,
                                                        @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(applicationEmployeeService.review(id, lang));
    }

    @PatchMapping("/complete/{id}")
    public ResponseEntity<AppResponse<String>> complete(@PathVariable String id,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(applicationEmployeeService.complete(id, lang));
    }

    public static int getCurrentPage(Integer page) {
        return page > 0 ? page - 1 : 1;
    }

}
