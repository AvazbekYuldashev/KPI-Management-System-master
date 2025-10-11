package api.v1.KPI.Management.System.department.controller.owner;


import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.department.dto.core.DepartmentResponseDTO;
import api.v1.KPI.Management.System.department.dto.owner.DepartmentOwnerCreateDTO;
import api.v1.KPI.Management.System.department.dto.owner.DepartmentOwnerUpdateDTO;
import api.v1.KPI.Management.System.department.service.owner.DepartmentOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owner/department")
@PreAuthorize("hasRole('OWNER')")
public class DepartmentOwnerController {
    @Autowired
    private DepartmentOwnerService departmentOwnerService;

    @PostMapping("")
    public ResponseEntity<DepartmentResponseDTO> create(@RequestBody DepartmentOwnerCreateDTO dto,
                                                         @RequestHeader(value = "Accept-Language", defaultValue = "UZ") String lang){
        return ResponseEntity.ok().body(departmentOwnerService.createOwner(dto, lang));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppResponse<String>> update(@RequestBody DepartmentOwnerUpdateDTO dto,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") String lang){
        return ResponseEntity.ok().body(departmentOwnerService.update(dto, lang));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppResponse<String>> delete(@PathVariable("id") String id,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") String lang){
        return ResponseEntity.ok().body(departmentOwnerService.delete(id, lang));
    }
}
