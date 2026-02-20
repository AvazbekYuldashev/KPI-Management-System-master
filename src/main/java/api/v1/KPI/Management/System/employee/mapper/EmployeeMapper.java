package api.v1.KPI.Management.System.employee.mapper;

import api.v1.KPI.Management.System.attach.service.AttachService;
import api.v1.KPI.Management.System.employee.dto.EmployeeResponseDTO;
import api.v1.KPI.Management.System.profile.entity.ProfileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    @Autowired
    private AttachService  attachService;

    public EmployeeResponseDTO toResponseDTO( ProfileEntity entity) {
        EmployeeResponseDTO dto = new EmployeeResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setUsername(entity.getUsername());
        dto.setPhoto(attachService.getDTOById(entity.getPhotoId()));
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setInProgress(entity.getInProgress());
        return dto;
    }
}
