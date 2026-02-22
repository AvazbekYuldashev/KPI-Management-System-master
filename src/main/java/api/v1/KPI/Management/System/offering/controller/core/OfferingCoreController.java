package api.v1.KPI.Management.System.offering.controller.core;

import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.building.dto.core.BuildingResponseDTO;
import api.v1.KPI.Management.System.offering.dto.core.OfferingResponseDTO;
import api.v1.KPI.Management.System.offering.service.core.OfferingCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping("/by-department/{id}")
    public ResponseEntity<Page<OfferingResponseDTO>> getAllByDepartmentIdPage(@PathVariable String id,
                                                                             @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                                             @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(offeringCoreService.getAllByDepartmentIdPage(id, getCurrentPage(page), size, lang));
    }

    @GetMapping("/by-building/{id}")
    public ResponseEntity<Page<OfferingResponseDTO>> getAllByBuildingIdPage(@PathVariable String id,
                                                                             @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                                             @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(offeringCoreService.getAllByBuildingIdPage(id, getCurrentPage(page), size, lang));
    }

    @GetMapping("/by-category/{id}")
    public ResponseEntity<Page<OfferingResponseDTO>> getAllPageByCategoryId(@PathVariable String id,
                                                                      @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                                      @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(offeringCoreService.getAllPageByCategoryId(id, getCurrentPage(page), size, lang));
    }

    public static int getCurrentPage(Integer page) {
        return (page != null && page > 0) ? page - 1 : 0;
    }

}
