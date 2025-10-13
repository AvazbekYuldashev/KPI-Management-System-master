package api.v1.KPI.Management.System.offering.controller.admin;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.offering.dto.admin.OfferingAdminUpdateDTO;
import api.v1.KPI.Management.System.offering.service.admin.OfferingAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/offering")
@PreAuthorize("hasRole('ADMIN')")
public class OfferingAdminController {
    @Autowired
    private OfferingAdminService offeringAdminService;

    @PutMapping("/{id}")
    public ResponseEntity<AppResponse<String>> update(@RequestBody OfferingAdminUpdateDTO dto,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") String lang){
        return ResponseEntity.ok().body(offeringAdminService.update(dto, lang));
    }
}
