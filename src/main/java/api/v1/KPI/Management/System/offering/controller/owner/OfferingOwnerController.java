package api.v1.KPI.Management.System.offering.controller.owner;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;

import api.v1.KPI.Management.System.department.dto.core.DepartmentResponseDTO;
import api.v1.KPI.Management.System.offering.dto.core.OfferingResponseDTO;
import api.v1.KPI.Management.System.offering.dto.owner.OfferingCreateDTO;
import api.v1.KPI.Management.System.offering.dto.owner.OfferingUpdateDTO;
import api.v1.KPI.Management.System.offering.service.owner.OfferingOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/offering-owner")
public class OfferingOwnerController {
    @Autowired
    private OfferingOwnerService offeringOwnerService;
    @PostMapping("/create")
    public ResponseEntity<OfferingResponseDTO> create(@RequestBody OfferingCreateDTO dto,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(offeringOwnerService.ownerCreate(dto,lang));
    }
    @PutMapping("/update")
    public ResponseEntity<AppResponse<String>> update(@RequestBody OfferingUpdateDTO dto,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(offeringOwnerService.ownerUpdate(dto, lang));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<OfferingResponseDTO>> findAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                               @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                               @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(offeringOwnerService.getAllPage(getCurrentPage(page), size, lang));
    }

    public static int getCurrentPage(Integer page) {
        return (page != null && page > 0) ? page - 1 : 0;
    }


}
