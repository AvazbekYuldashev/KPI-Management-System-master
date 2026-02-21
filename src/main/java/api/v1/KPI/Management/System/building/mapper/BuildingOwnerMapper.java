package api.v1.KPI.Management.System.building.mapper;

import api.v1.KPI.Management.System.building.dto.owner.BuildingOwnerCreateDTO;
import api.v1.KPI.Management.System.building.dto.owner.BuildingOwnerUpdateDTO;
import api.v1.KPI.Management.System.building.entity.BuildingEntity;
import org.springframework.stereotype.Component;

@Component
public class BuildingOwnerMapper {
    public BuildingEntity toCreatedEntity(BuildingOwnerCreateDTO dto){
        BuildingEntity entity = new BuildingEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setDepartmentId(dto.getDepartmentId());
        entity.setChiefId(dto.getChiefId());
        return entity;
    }
    public BuildingEntity toUpdatedEntity(BuildingOwnerUpdateDTO dto) {
        BuildingEntity entity = new BuildingEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setDepartmentId(dto.getDepartmentId());
        entity.setChiefId(dto.getChiefId());
        return entity;
    }
}
