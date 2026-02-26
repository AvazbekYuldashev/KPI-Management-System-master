package api.v1.KPI.Management.System.building.mapper;

import api.v1.KPI.Management.System.building.dto.admin.BuildingAdminUpdateDTO;
import api.v1.KPI.Management.System.building.entity.BuildingEntity;
import org.springframework.stereotype.Component;

@Component
public class BuildingAdminMapper {

    public BuildingEntity toUpdateEntity(BuildingAdminUpdateDTO dto) {
        BuildingEntity entity = new BuildingEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        return entity;
    }
}
