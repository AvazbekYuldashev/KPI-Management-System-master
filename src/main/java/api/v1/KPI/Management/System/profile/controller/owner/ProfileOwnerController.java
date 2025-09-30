package api.v1.KPI.Management.System.profile.controller.owner;


import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.profile.dto.ProfileResponseDTO;
import api.v1.KPI.Management.System.profile.dto.owner.*;
import api.v1.KPI.Management.System.profile.dto.user.ProfilePhotoUpdate;
import api.v1.KPI.Management.System.profile.service.owner.ProfileOwnerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owner/profile")
@PreAuthorize("hasRole('OWNER')")
public class    ProfileOwnerController {
    @Autowired
    private ProfileOwnerService profileOwnerService;

    @GetMapping("/all")
    public ResponseEntity<PageImpl<ProfileResponseDTO>> getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                @RequestParam(value = "size", defaultValue = "15") int size) {
        return ResponseEntity.ok().body(profileOwnerService.getAll(getCurrentPage(page), size));
    }


    @PutMapping("/photo")
    public ResponseEntity<AppResponse<String>> updatePhoto(@Valid @RequestBody ProfilePhotoUpdate dto,
                                                           @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(profileOwnerService.updatePhoto(dto.getPhotoId(), lang));
    }

    @PatchMapping("/status")
    public ResponseEntity<AppResponse<String>> changeStatus(@Valid @RequestBody ProfileOwnerChangeStatusDTO dto,
                                                            @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(profileOwnerService.changeStatus(dto, lang));
    }

    @PatchMapping("/role")
    public ResponseEntity<AppResponse<String>> changeRole(@Valid @RequestBody ProfileOwnerChangeRoleDTO dto,
                                                          @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(profileOwnerService.changeRole(dto, lang));
    }

    @PatchMapping("/password")
    public ResponseEntity<AppResponse<String>> updatePassword(@Valid @RequestBody ProfileOwnerUpdatePassword dto,
                                                              @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(profileOwnerService.changePassword(dto, lang));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<AppResponse<String>> delete(@PathVariable("id") String id,
                                                       @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang ) {

        return ResponseEntity.ok().body(profileOwnerService.deletebyId(id, lang));
    }


    @PostMapping("/filter")
    public ResponseEntity<Page<ProfileResponseDTO>> filter(@RequestBody ProfileFilterDTO dto,
                                                           @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                           @RequestParam(value = "size", defaultValue = "10") Integer size){
        return ResponseEntity.ok().body(profileOwnerService.filter(dto, getCurrentPage(page), size));
    }




    public static int getCurrentPage(Integer page) {
        return page > 0 ? page - 1 : 1;
    }
}
