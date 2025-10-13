package api.v1.KPI.Management.System.offering.controller.core;

import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.offering.dto.core.OfferingResponseDTO;
import api.v1.KPI.Management.System.offering.service.core.OfferingCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/core/offering")
@PreAuthorize("hasAnyRole('OWNER','ADMIN', 'MANAGER', 'EMPLOYEE', 'USER')")
public class OfferingCoreController {
    @Autowired
    private OfferingCoreService offeringCoreService;

    @GetMapping("/{id}")
    public ResponseEntity<OfferingResponseDTO> getById(@PathVariable String id,
                                                       @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(offeringCoreService.getById(id, lang));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<OfferingResponseDTO>> getAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                            @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                            @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(offeringCoreService.getAll(getCurrentPage(page), size, lang));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Page<OfferingResponseDTO>> getByCategoryId(@PathVariable String id,
                                                                       @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                                       @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(offeringCoreService.getByCategoryId(id, getCurrentPage(page), size, lang));
    }

    public static int getCurrentPage(Integer page) {
        return page > 0 ? page - 1 : 1;
    }
}
