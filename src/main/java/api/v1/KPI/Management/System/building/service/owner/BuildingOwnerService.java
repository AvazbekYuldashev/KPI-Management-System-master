package api.v1.KPI.Management.System.building.service.owner;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.building.dto.core.BuildingResponseDTO;
import api.v1.KPI.Management.System.building.dto.owner.BuildingOwnerCreateDTO;
import api.v1.KPI.Management.System.building.dto.owner.BuildingOwnerUpdateDTO;
import api.v1.KPI.Management.System.building.mapper.BuildingMapper;
import api.v1.KPI.Management.System.building.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildingOwnerService extends BuildingService {

    @Autowired
    private BuildingMapper buildingMapper;

    public BuildingResponseDTO createOwner(BuildingOwnerCreateDTO dto, String lang) {
        return buildingMapper.toResponseDTO(create(buildingMapper.toCreatedEntity(dto)));
    }

    public AppResponse<String> update(BuildingOwnerUpdateDTO dto, String lang) {
        return changeDetail(buildingMapper.toUpdatedEntity(dto));
    }

    public AppResponse<String> delete(String id, String lang) {
        return deleteById(id);
    }
}
