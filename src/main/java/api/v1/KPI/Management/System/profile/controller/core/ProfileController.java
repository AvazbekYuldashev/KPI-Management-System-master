package api.v1.KPI.Management.System.profile.controller.core;

import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.profile.dto.profile.ProfileResponseDTO;
import api.v1.KPI.Management.System.profile.service.core.ProfileCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accaunt")
@PreAuthorize("hasAnyRole('USER', 'EMPLOYEE', 'MANAGER', 'ADMIN','OWNER')")
public class ProfileController {
    @Autowired
    private ProfileCoreService profileCoreService;

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponseDTO> getById(@PathVariable String id,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang ) {
        return ResponseEntity.ok().body(profileCoreService.getById(id, lang));
    }

    @GetMapping("/managers")
    public ResponseEntity<List<ProfileResponseDTO>> getAllManager(@RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang ) {
        return ResponseEntity.ok().body(profileCoreService.findByRole(lang));
    }

}
