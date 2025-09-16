package api.v1.KPI.Management.System.offering.controller.admin;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.offering.dto.OfferingResponseDTO;
import api.v1.KPI.Management.System.offering.dto.admin.OfferingAdminCreateDTO;
import api.v1.KPI.Management.System.offering.dto.admin.OfferingAdminUpdateDTO;
import api.v1.KPI.Management.System.offering.service.admin.OfferingAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/offering")
@PreAuthorize("hasRole('ADMIN')")
public class OfferingAdminController {
    @Autowired
    private OfferingAdminService offeringAdminService;

    @PostMapping("")
    public ResponseEntity<OfferingResponseDTO> createDepartment(@RequestBody OfferingAdminCreateDTO dto) {
        return ResponseEntity.ok().body(offeringAdminService.adminCreate(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferingResponseDTO> getDepartment(@PathVariable String id) {
        return ResponseEntity.ok().body(offeringAdminService.adminGetById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<PageImpl<OfferingResponseDTO>> getAllDepartments(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                             @RequestParam(value = "size", defaultValue = "15") int size){
        return ResponseEntity.ok().body(offeringAdminService.adminGetAll(getCurrentPage(page), size));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppResponse<String>> deleteDepartment(@PathVariable String id) {
        return ResponseEntity.ok().body(offeringAdminService.adminDelete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppResponse<String>> updateDepartment(@PathVariable String id,
                                                                @RequestBody OfferingAdminUpdateDTO dto) {
        return ResponseEntity.ok().body(offeringAdminService.adminUpdate(id, dto));
    }

    public static int getCurrentPage(Integer page) {
        return page > 0 ? page - 1 : 1;
    }
}
