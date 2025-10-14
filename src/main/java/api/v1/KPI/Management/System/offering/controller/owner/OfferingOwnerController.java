package api.v1.KPI.Management.System.offering.controller.owner;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.offering.dto.core.OfferingResponseDTO;
import api.v1.KPI.Management.System.offering.dto.owner.OfferingOwnerCreateDTO;
import api.v1.KPI.Management.System.offering.dto.owner.OfferingOwnerUpdateDTO;
import api.v1.KPI.Management.System.offering.service.owner.OfferingOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owner/offering")
@PreAuthorize("hasRole('OWNER')")
public class OfferingOwnerController {
    @Autowired
    private OfferingOwnerService offeringOwnerService;

    @PostMapping("")
    public ResponseEntity<OfferingResponseDTO> create(@RequestBody OfferingOwnerCreateDTO dto,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") String lang){
        return ResponseEntity.ok().body(offeringOwnerService.createOwner(dto, lang));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppResponse<String>> update(@RequestBody OfferingOwnerUpdateDTO dto,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") String lang){
        return ResponseEntity.ok().body(offeringOwnerService.update(dto, lang));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppResponse<String>> delete(@PathVariable("id") String id,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") String lang){
        return ResponseEntity.ok().body(offeringOwnerService.delete(id, lang));
    }
}
