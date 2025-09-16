package api.v1.KPI.Management.System.application.controller.admin;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.application.dto.ApplicationResponseDTO;
import api.v1.KPI.Management.System.application.dto.admin.ApplicationAdminCreateDTO;
import api.v1.KPI.Management.System.application.dto.admin.ApplicationAdminUpdateDTO;
import api.v1.KPI.Management.System.application.service.admin.ApplicationAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/application")
@PreAuthorize("hasRole('ADMIN')")
public class ApplicationAdminController {
    @Autowired
    private ApplicationAdminService applicationAdminService;

    @PostMapping("")
    public ResponseEntity<ApplicationResponseDTO> createDepartment(@RequestBody ApplicationAdminCreateDTO dto) {
        return ResponseEntity.ok().body(applicationAdminService.adminCreate(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationResponseDTO> getDepartment(@PathVariable String id) {
        return ResponseEntity.ok().body(applicationAdminService.adminGetById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<PageImpl<ApplicationResponseDTO>> getAllDepartments(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                           @RequestParam(value = "size", defaultValue = "15") int size){
        return ResponseEntity.ok().body(applicationAdminService.adminGetAll(getCurrentPage(page), size));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppResponse<String>> deleteDepartment(@PathVariable String id) {
        return ResponseEntity.ok().body(applicationAdminService.adminDelete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppResponse<String>> updateDepartment(@PathVariable String id,
                                                                @RequestBody ApplicationAdminUpdateDTO dto) {
        return ResponseEntity.ok().body(applicationAdminService.adminUpdate(id, dto));
    }

    public static int getCurrentPage(Integer page) {
        return page > 0 ? page - 1 : 1;
    }
}
