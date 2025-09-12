package api.v1.KPI.Management.System.building.mapper;

import api.v1.KPI.Management.System.building.dto.BuildingResponseDTO;
import api.v1.KPI.Management.System.building.dto.admin.BuildingAdminCreateDTO;
import api.v1.KPI.Management.System.building.entity.BuildingEntity;
import org.springframework.stereotype.Component;

@Component
public class BuildingMapper {

    public BuildingResponseDTO toResponseDTO(BuildingEntity entity) {
        BuildingResponseDTO dto = new BuildingResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setChiefId(entity.getChiefId());
        dto.setDepartmentId(entity.getDepartmentId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedDate(entity.getUpdatedDate());
        dto.setVisible(entity.getVisible());
        return dto;
    }

    public BuildingEntity toAdminCreateEntity(BuildingAdminCreateDTO dto) {
        BuildingEntity entity = new BuildingEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setChiefId(dto.getChiefId());
        entity.setDepartmentId(dto.getDepartmentId());
        return entity;
    }
}
