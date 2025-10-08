package api.v1.KPI.Management.System.building.service.owner;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.building.dto.BuildingResponseDTO;
import api.v1.KPI.Management.System.building.dto.owner.BuildingOwnerCreateDTO;
import api.v1.KPI.Management.System.building.dto.owner.BuildingOwnerUpdateDTO;
import api.v1.KPI.Management.System.building.entity.BuildingEntity;
import api.v1.KPI.Management.System.building.mapper.BuildingMapper;
import api.v1.KPI.Management.System.building.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildingOwnerService extends BuildingService {
    @Autowired
    private BuildingMapper buildingMapper;


    public BuildingResponseDTO ownerCreate(BuildingOwnerCreateDTO dto) {
        BuildingEntity entity = buildingMapper.toEntity(dto);
        return buildingMapper.toResponseDTO(create(entity));
    }

    public AppResponse<String> ownerDelete(String id, AppLanguage lang) {
        BuildingEntity entity = findById(id);
        return deleteById(id);
    }

    public AppResponse<String> ownerUpdate(String id, BuildingOwnerUpdateDTO dto) {
        BuildingEntity entity = findById(id);
        return updateDetail(id, buildingMapper.toEntity(dto));
    }

}
