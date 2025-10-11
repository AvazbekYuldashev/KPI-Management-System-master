package api.v1.KPI.Management.System.department.mapper;

import api.v1.KPI.Management.System.department.dto.admin.DepartmentAdminUpdateDTO;
import api.v1.KPI.Management.System.department.dto.core.DepartmentResponseDTO;
import api.v1.KPI.Management.System.department.dto.owner.DepartmentOwnerCreateDTO;
import api.v1.KPI.Management.System.department.dto.owner.DepartmentOwnerUpdateDTO;
import api.v1.KPI.Management.System.department.entity.DepartmentEntity;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {

    public DepartmentEntity toCreatedEntity(DepartmentOwnerCreateDTO dto) {
        DepartmentEntity entity = new DepartmentEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setChiefId(dto.getChiefId());
        return entity;
    }

    public DepartmentResponseDTO toResponseDTO(DepartmentEntity entity) {
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

    public DepartmentEntity toUpdatedEntity(DepartmentOwnerUpdateDTO dto) {
        DepartmentEntity entity = new DepartmentEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setChiefId(dto.getChiefId());
        return entity;
    }

    public DepartmentEntity toUpdatedEntity(DepartmentAdminUpdateDTO dto) {
        DepartmentEntity entity = new DepartmentEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        return entity;

    }
}
