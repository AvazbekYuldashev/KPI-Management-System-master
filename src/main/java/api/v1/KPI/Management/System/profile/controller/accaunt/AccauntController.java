package api.v1.KPI.Management.System.profile.controller.accaunt;


import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.profile.dto.profile.ProfileDTO;
import api.v1.KPI.Management.System.profile.dto.profile.ProfileDetailUpdateDTO;
import api.v1.KPI.Management.System.profile.dto.profile.ProfilePasswordUpdate;
import api.v1.KPI.Management.System.profile.dto.profile.ProfilePhotoUpdate;
import api.v1.KPI.Management.System.profile.dto.profile.ProfileUsernameUpdateDTO;
import api.v1.KPI.Management.System.profile.service.profile.AccauntService;
import api.v1.KPI.Management.System.security.dto.CodeConfirmDTO;
import api.v1.KPI.Management.System.security.util.SpringSecurityUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")

public class AccauntController {
    @Autowired
    private AccauntService accauntService;

    @GetMapping("")
    public ResponseEntity<ProfileDTO> getMe(@RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(accauntService.getMe(lang));
    }

    @PutMapping("/detail")
    public ResponseEntity<AppResponse<String>> update(@Valid @RequestBody ProfileDetailUpdateDTO dto,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(accauntService.updateDetail(dto, lang));
    }

    @PutMapping("/photo")
    public ResponseEntity<AppResponse<String>> updatePhoto(@Valid @RequestBody ProfilePhotoUpdate dto,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(accauntService.updatePhoto(dto.getPhotoId(), lang));
    }

    @PutMapping("/password")
    public ResponseEntity<AppResponse<String>> updatePassword(@Valid @RequestBody ProfilePasswordUpdate dto,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(accauntService.updatePassword(dto, lang));
    }

    @PutMapping("/username")
    public ResponseEntity<AppResponse<String>> updateUsername(@Valid @RequestBody ProfileUsernameUpdateDTO dto,
                                                              @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(accauntService.updateUsername(dto, lang));
    }

    @PutMapping("/username/confirm")
    public ResponseEntity<AppResponse<String>> updateUsernameConfirm(@Valid @RequestBody CodeConfirmDTO dto,
                                                                     @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(accauntService.updateUsernameConfirm(dto, lang));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppResponse<String>> delete(@RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang ) {
        return ResponseEntity.ok().body(accauntService.deletebyId(SpringSecurityUtil.getCurrentUserId(), lang));
    }
}
