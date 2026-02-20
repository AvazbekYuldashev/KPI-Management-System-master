package api.v1.KPI.Management.System.department.mapper;

import api.v1.KPI.Management.System.department.dto.core.DepartmentCreateDTO;
import api.v1.KPI.Management.System.department.dto.core.DepartmentUpdateDTO;
import api.v1.KPI.Management.System.department.dto.core.DepartmentResponseDTO;
import api.v1.KPI.Management.System.department.dto.manager.DepartmentManagerUpdateDTO;
import api.v1.KPI.Management.System.department.entity.DepartmentEntity;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {
    public DepartmentEntity toCreatedEntity(DepartmentCreateDTO dto){
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
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setChiefId(dto.getChiefId());
        return entity;
    }

    public DepartmentEntity toUpdatedEntity(DepartmentManagerUpdateDTO dto) {
        DepartmentEntity entity = new DepartmentEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        return entity;
    }
}
