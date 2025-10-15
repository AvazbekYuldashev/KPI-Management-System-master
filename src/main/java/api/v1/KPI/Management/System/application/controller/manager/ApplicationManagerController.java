package api.v1.KPI.Management.System.application.controller.manager;


import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.application.dto.core.ApplicationResponseDTO;
import api.v1.KPI.Management.System.application.service.manager.ApplicationManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager/application")
@PreAuthorize("hasRole('MANAGER')")
public class ApplicationManagerController {
    @Autowired
    private ApplicationManagerService applicationManagerService;

    @GetMapping("/all")
    public ResponseEntity<Page<ApplicationResponseDTO>> getAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                               @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                               @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(applicationManagerService.getAll(getCurrentPage(page), size, lang));
    }

    @PatchMapping("/approve/{id}")
    public ResponseEntity<AppResponse<String>> adminChecked(@PathVariable String id,
                                                        @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(applicationManagerService.adminChecked(id, lang));
    }

    @PatchMapping("/reject/{id}")

    public static int getCurrentPage(Integer page) {
        return page > 0 ? page - 1 : 1;
    }

}
