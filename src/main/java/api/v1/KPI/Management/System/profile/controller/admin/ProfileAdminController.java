package api.v1.KPI.Management.System.profile.controller.admin;


import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.profile.dto.admin.ProfileAdminChangeRoleDTO;
import api.v1.KPI.Management.System.profile.dto.admin.ProfileAdminChangeStatusDTO;
import api.v1.KPI.Management.System.profile.service.admin.ProfileAdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/profile")
@PreAuthorize("hasAnyRole('ADMIN')")
public class ProfileAdminController {
    @Autowired
    private ProfileAdminService profileAdminService;

    @PatchMapping("/status")
    public ResponseEntity<AppResponse<String>> changeStatus(@Valid @RequestBody ProfileAdminChangeStatusDTO dto,
                                                            @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(profileAdminService.changeStatus(dto, lang));
    }

    @PatchMapping("/role")
    public ResponseEntity<AppResponse<String>> changeRole(@Valid @RequestBody ProfileAdminChangeRoleDTO dto,
                                                          @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(profileAdminService.changeRole(dto, lang));
    }



}
