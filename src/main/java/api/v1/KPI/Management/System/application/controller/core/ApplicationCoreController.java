package api.v1.KPI.Management.System.application.controller.core;

import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.application.dto.core.ApplicationCreateDTO;
import api.v1.KPI.Management.System.application.dto.core.ApplicationResponseDTO;
import api.v1.KPI.Management.System.application.service.core.ApplicationCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/application-core")
public class ApplicationCoreController {

    @Autowired
    private ApplicationCoreService applicationCoreService;

    @PostMapping("")
    public ResponseEntity<ApplicationResponseDTO> create(@RequestBody ApplicationCreateDTO dto,
                                                         @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(applicationCoreService.create(dto, lang));
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<ApplicationResponseDTO> findById(@PathVariable String id,
                                                           @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(applicationCoreService.getById(id, lang));
    }
}
