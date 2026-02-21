package api.v1.KPI.Management.System.building.mapper;

import api.v1.KPI.Management.System.building.dto.manager.BuildingManagerCreateDTO;
import api.v1.KPI.Management.System.building.dto.manager.BuildingManagerUpdateDTO;
import api.v1.KPI.Management.System.building.entity.BuildingEntity;
import org.springframework.stereotype.Component;

@Component
public class BuildingManagerMapper {
    public BuildingEntity toUpdatedEntity(BuildingManagerUpdateDTO dto) {
        BuildingEntity entity = new BuildingEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setChiefId(dto.getChiefId());
        return entity;
    }

    public BuildingEntity toCreatedEntity(BuildingManagerCreateDTO dto) {
        BuildingEntity entity = new BuildingEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setChiefId(dto.getChiefId());
        return entity;
    }
}
