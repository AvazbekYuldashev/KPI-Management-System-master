package api.v1.KPI.Management.System.application.controller.user;


import api.v1.KPI.Management.System.application.dto.ApplicationResponseDTO;
import api.v1.KPI.Management.System.application.dto.user.ApplicationUserCreateDTO;
import api.v1.KPI.Management.System.application.service.user.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
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
    public ResponseEntity<ApplicationResponseDTO> create(@RequestBody ApplicationUserCreateDTO dto) {
        return ResponseEntity.ok().body(applicationUserService.userCreate(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationResponseDTO> getById(@PathVariable String id) {
        return ResponseEntity.ok().body(applicationUserService.userGetById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<PageImpl<ApplicationResponseDTO>> getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                           @RequestParam(value = "size", defaultValue = "15") int size){
        return ResponseEntity.ok().body(applicationUserService.userGetAll(getCurrentPage(page), size));
    }

    public static int getCurrentPage(Integer page) {
        return page > 0 ? page - 1 : 1;
    }

}
