package api.v1.KPI.Management.System.department.mapper;

import api.v1.KPI.Management.System.department.dto.core.DepartmentCreatedDTO;
import api.v1.KPI.Management.System.department.dto.core.DepartmentUpdateDTO;
import api.v1.KPI.Management.System.department.dto.core.DepartmentResponseDTO;
import api.v1.KPI.Management.System.department.entity.DepartmentEntity;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {
    public DepartmentEntity toCreatedEntity(DepartmentCreatedDTO dto){
        DepartmentEntity entity = new DepartmentEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setChiefId(dto.getChiefId());
        return entity;
    }

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


    public DepartmentEntity toUpdatedEntity(DepartmentUpdateDTO dto) {
        DepartmentEntity entity = new DepartmentEntity();
        entity.setTitle(entity.getTitle());
        entity.setDescription(entity.getDescription());
        entity.setChiefId(dto.getChiefId());
        return entity;
    }
}
