package api.v1.KPI.Management.System.offering.controller.user;

import api.v1.KPI.Management.System.offering.dto.OfferingResponseDTO;
import api.v1.KPI.Management.System.offering.service.user.OfferingUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/offering")
@PreAuthorize("hasRole('USER')")
public class OfferingUserController {
    @Autowired
    private OfferingUserService offeringUserService;

    @GetMapping("/{id}")
    public ResponseEntity<OfferingResponseDTO> getDepartment(@PathVariable String id) {
        return ResponseEntity.ok().body(offeringUserService.userGetById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<PageImpl<OfferingResponseDTO>> getAllDepartments(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                             @RequestParam(value = "size", defaultValue = "15") int size){
        return ResponseEntity.ok().body(offeringUserService.userGetAll(getCurrentPage(page), size));
    }

    public static int getCurrentPage(Integer page) {
        return page > 0 ? page - 1 : 1;
    }

}
