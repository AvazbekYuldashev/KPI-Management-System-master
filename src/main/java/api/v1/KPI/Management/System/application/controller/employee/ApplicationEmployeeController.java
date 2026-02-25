package api.v1.KPI.Management.System.application.controller.employee;


import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.application.dto.core.ApplicationResponseDTO;
import api.v1.KPI.Management.System.application.dto.core.ApplicationStatusDTO;
import api.v1.KPI.Management.System.application.dto.manager.ApplicationFilterDTO;
import api.v1.KPI.Management.System.application.service.employee.ApplicationEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/application-employee")
public class ApplicationEmployeeController {
    @Autowired
    private ApplicationEmployeeService applicationEmployeeService;

    @PostMapping("/filter")
    public ResponseEntity<Page<ApplicationResponseDTO>> filter(@RequestBody ApplicationFilterDTO dto,
                                                               @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                               @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                               @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(applicationEmployeeService.searchApplications(dto, getCurrentPage(page), size, lang));
    }

    @PatchMapping("/status")
    public ResponseEntity<AppResponse<String>> updateStatus(@RequestBody ApplicationStatusDTO dto,
                                                            @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(applicationEmployeeService.updateStatus(dto, lang));
    }

    public static int getCurrentPage(Integer page) {
        return (page != null && page > 0) ? page - 1 : 0;
    }


}
