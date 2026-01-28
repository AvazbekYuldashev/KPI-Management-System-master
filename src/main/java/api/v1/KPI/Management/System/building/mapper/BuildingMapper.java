package api.v1.KPI.Management.System.building.mapper;

import api.v1.KPI.Management.System.building.dto.core.BuildingCreatedDTO;
import api.v1.KPI.Management.System.building.dto.core.BuildingResponseDTO;
import api.v1.KPI.Management.System.building.dto.core.BuildingUpdateDTO;
import api.v1.KPI.Management.System.building.entity.BuildingEntity;
import org.springframework.stereotype.Component;

@Component
public class BuildingMapper {
    public BuildingEntity toCreatedEntity(BuildingCreatedDTO dto){
        BuildingEntity entity = new BuildingEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setDepartmentId(dto.getDepartmentId());
        entity.setChiefId(dto.getChiefId());
        return entity;
    }
    public BuildingResponseDTO toResponseDTO(BuildingEntity entity){
        BuildingResponseDTO dto = new BuildingResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setDepartmentId(entity.getDepartmentId());
        dto.setChiefId(entity.getChiefId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedDate(entity.getUpdatedDate());
        dto.setVisible(entity.getVisible());
        return dto;
    }

    public BuildingEntity toUpdatedEntity(BuildingUpdateDTO dto) {
        BuildingEntity entity = new BuildingEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setDepartmentId(dto.getDepartmentId());
        entity.setChiefId(dto.getChiefId());
        return entity;
    }
}
