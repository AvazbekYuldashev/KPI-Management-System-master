package api.v1.KPI.Management.System.building.service.admin;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.building.dto.BuildingResponseDTO;
import api.v1.KPI.Management.System.building.dto.admin.BuildingAdminCreateDTO;
import api.v1.KPI.Management.System.building.dto.admin.BuildingAdminUpdateDTO;
import api.v1.KPI.Management.System.building.mapper.BuildingMapper;
import api.v1.KPI.Management.System.building.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

@Service
public class BuildingAdminService extends BuildingService {
    @Autowired
    private BuildingMapper buildingMapper;
    public BuildingResponseDTO adminCreate(BuildingAdminCreateDTO dto) {
        return buildingMapper.toResponseDTO(create(buildingMapper.toAdminCreateEntity(dto)));
    }

    public BuildingResponseDTO adminGetById(String id) {
        return buildingMapper.toResponseDTO(findById(id));
    }

    public PageImpl<BuildingResponseDTO> adminGetAll(int page, int size) {
        return getAll(page, size);
    }

    public AppResponse<String> adminDelete(String id) {
        return deleteById(id);
    }

    public AppResponse<String> adminUpdate(String id, BuildingAdminUpdateDTO dto) {
        return updateDetail(id, dto);
    }

    //
}
