package api.v1.KPI.Management.System.building.service.owner;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.app.util.AppResponseUtil;
import api.v1.KPI.Management.System.building.dto.core.BuildingCreateDTO;
import api.v1.KPI.Management.System.building.dto.core.BuildingResponseDTO;
import api.v1.KPI.Management.System.building.dto.core.BuildingUpdateDTO;
import api.v1.KPI.Management.System.building.entity.BuildingEntity;
import api.v1.KPI.Management.System.building.mapper.BuildingMapper;
import api.v1.KPI.Management.System.building.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildingOwnerService extends BuildingService {
    @Autowired
    private BuildingMapper buildingMapper;

    public BuildingResponseDTO ownerCreate(BuildingCreateDTO dto, AppLanguage lang){
        BuildingEntity entity = buildingMapper.toCreatedEntity(dto);
        return buildingMapper.toResponseDTO(create(entity));
    }

    public AppResponse<String> ownerUpdate(BuildingUpdateDTO dto, AppLanguage lang){
        BuildingEntity entity = buildingMapper.toUpdatedEntity(dto);
        return AppResponseUtil.chek(update(entity, lang));
    }
}
