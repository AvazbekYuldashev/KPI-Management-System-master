package api.v1.KPI.Management.System.profile.controller.core;


import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.profile.dto.profile.ProfileDTO;
import api.v1.KPI.Management.System.profile.service.profile.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/core/profile")
@PreAuthorize("hasAnyRole('OWNER','ADMIN', 'MANAGER', 'EMPLOYEE', 'USER')")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDTO> getById(@PathVariable String id,
                                              @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(profileService.getById(id, lang));
    }
    @GetMapping("/me")
    public ResponseEntity<ProfileDTO> getMe(@RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(profileService.getMe(lang));
    }


}
