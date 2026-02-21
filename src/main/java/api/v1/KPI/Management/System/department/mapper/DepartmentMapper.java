package api.v1.KPI.Management.System.department.mapper;

import api.v1.KPI.Management.System.department.dto.core.DepartmentResponseDTO;
import api.v1.KPI.Management.System.department.entity.DepartmentEntity;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {

    public DepartmentResponseDTO toResponseDTO(DepartmentEntity entity){
        DepartmentResponseDTO dto = new DepartmentResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setChiefId(entity.getChiefId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedDate(entity.getUpdatedDate());
        dto.setVisible(entity.getVisible());
        return dto;
    }
}
