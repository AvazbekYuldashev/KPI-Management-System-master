package api.v1.KPI.Management.System.application.controller.user;


import api.v1.KPI.Management.System.application.dto.core.ApplicationCreateDTO;
import api.v1.KPI.Management.System.application.dto.core.ApplicationResponseDTO;
import api.v1.KPI.Management.System.application.service.user.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/application")
@PreAuthorize("hasRole('USER')")
public class ApplicationUserController {
    @Autowired
    private ApplicationUserService applicationUserService;

    @PostMapping("")
    public ResponseEntity<ApplicationResponseDTO> create(@RequestBody ApplicationCreateDTO dto,
                                                         @RequestHeader(value = "Accept-Language", defaultValue = "UZ") String lang){
        return ResponseEntity.ok().body(applicationUserService.createUser(dto, lang));
    }


}
