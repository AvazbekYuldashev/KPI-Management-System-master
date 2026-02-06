package api.v1.KPI.Management.System.offering.controller.owner;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;

import api.v1.KPI.Management.System.offering.dto.core.OfferingResponseDTO;
import api.v1.KPI.Management.System.offering.dto.owner.OfferingCreateDTO;
import api.v1.KPI.Management.System.offering.dto.owner.OfferingUpdateDTO;
import api.v1.KPI.Management.System.offering.service.owner.OfferingOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/offering-admin")
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
}
