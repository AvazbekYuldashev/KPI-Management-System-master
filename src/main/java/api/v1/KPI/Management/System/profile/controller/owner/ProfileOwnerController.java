package api.v1.KPI.Management.System.profile.controller.owner;


import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.profile.dto.profile.ProfileResponseDTO;
import api.v1.KPI.Management.System.profile.dto.owner.*;
import api.v1.KPI.Management.System.profile.dto.profile.ProfilePhotoUpdate;
import api.v1.KPI.Management.System.profile.service.owner.ProfileOwnerService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile-owner")
@PreAuthorize("hasRole('OWNER')")
public class ProfileOwnerController {
    private final ProfileOwnerService profileOwnerService;

    public ProfileOwnerController(ProfileOwnerService profileOwnerService) {
        this.profileOwnerService = profileOwnerService;
    }


    @PostMapping("/create")
    public ResponseEntity<ProfileResponseDTO> create(@RequestBody ProfileOwnerCreateDTO dto,
                                                     @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(profileOwnerService.add(dto,lang));

    }

    @GetMapping("/all")
    public ResponseEntity<PageImpl<ProfileResponseDTO>> getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                @RequestParam(value = "size", defaultValue = "15") int size) {
        return ResponseEntity.ok().body(profileOwnerService.getAll(getCurrentPage(page), size));
    }

    @PatchMapping("/department")
    public ResponseEntity<AppResponse<String>> updateDepartment(@Valid @RequestBody ProfileOwnerChangeDepartmentDTO dto,
                                                                @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(profileOwnerService.updateDepartment(dto, lang));
    }

    @PutMapping("/photo")
    public ResponseEntity<AppResponse<String>> updatePhoto(@Valid @RequestBody ProfilePhotoUpdate dto,
                                                           @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(profileOwnerService.updatePhoto(dto, lang));
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
    public ResponseEntity<AppResponse<String>> delete(@PathVariable("id") String id,
                                                       @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang ) {

        return ResponseEntity.ok().body(profileOwnerService.deleteByIdAS(id, lang));
    }

    @PostMapping("/filter")
        public ResponseEntity<Page<ProfileResponseDTO>> filter(@RequestBody ProfileOwnerFilterDTO dto,
                                                           @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                           @RequestParam(value = "size", defaultValue = "10") Integer size){
        return ResponseEntity.ok().body(profileOwnerService.filter(dto, getCurrentPage(page), size));
    }

    public static int getCurrentPage(Integer page) {
        return page > 0 ? page - 1 : 0;
    }
}
