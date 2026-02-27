package api.v1.KPI.Management.System.offering.controller.manager;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.offering.dto.core.OfferingResponseDTO;
import api.v1.KPI.Management.System.offering.dto.manager.OfferingManagerCreateDTO;
import api.v1.KPI.Management.System.offering.dto.manager.OfferingManagerUpdateDTO;
import api.v1.KPI.Management.System.offering.service.manager.OfferingManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/offering-manager")
public class OfferingManagerController {
    @Autowired
    private OfferingManagerService offeringManagerService;
    @PostMapping("/create")
    public ResponseEntity<OfferingResponseDTO> create(@RequestBody OfferingManagerCreateDTO dto,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(offeringManagerService.managerCreate(dto,lang));
    }
    @PutMapping("/update")
    public ResponseEntity<AppResponse<String>> update(@RequestBody OfferingManagerUpdateDTO dto,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(offeringManagerService.managerUpdate(dto, lang));
    }

    public static int getCurrentPage(Integer page) {
        return (page != null && page > 0) ? page - 1 : 0;
    }


}
