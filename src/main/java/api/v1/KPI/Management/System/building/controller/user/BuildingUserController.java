package api.v1.KPI.Management.System.building.controller.user;

import api.v1.KPI.Management.System.building.dto.BuildingResponseDTO;
import api.v1.KPI.Management.System.building.service.user.BuildingUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user/building")
@PreAuthorize("hasRole('USER')")
public class BuildingUserController {
    @Autowired
    private BuildingUserService buildingUserService;
    @GetMapping("/{id}")
    public ResponseEntity<BuildingResponseDTO> getDepartment(@PathVariable String id) {
        return ResponseEntity.ok().body(buildingUserService.userGetById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<PageImpl<BuildingResponseDTO>> getAllDepartments(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                           @RequestParam(value = "size", defaultValue = "15") int size){
        return ResponseEntity.ok().body(buildingUserService.userGetAll(getCurrentPage(page), size));
    }

    public static int getCurrentPage(Integer page) {
        return page > 0 ? page - 1 : 1;
    }
}
