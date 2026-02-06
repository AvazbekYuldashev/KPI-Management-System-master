package api.v1.KPI.Management.System.offering.controller.core;

import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.offering.dto.core.OfferingResponseDTO;
import api.v1.KPI.Management.System.offering.service.core.OfferingCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/offering-core")
public class OfferingCoreController {
    @Autowired
    private OfferingCoreService offeringCoreService;

    @GetMapping("/by-id/{id}")
    public ResponseEntity<OfferingResponseDTO> findById(@PathVariable String id,
                                                        @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(offeringCoreService.getById(id, lang));
    }

    @GetMapping("/by-title/{title}")
    public ResponseEntity<OfferingResponseDTO> findByTitle(@PathVariable String title,
                                                             @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(offeringCoreService.getByTitle(title, lang));
    }
}
