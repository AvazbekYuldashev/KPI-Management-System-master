package api.v1.KPI.Management.System.building.service.admin;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.building.dto.admin.BuildingAdminCreateDTO;
import api.v1.KPI.Management.System.building.dto.admin.BuildingAdminUpdateDTO;
import api.v1.KPI.Management.System.building.dto.core.BuildingResponseDTO;
import api.v1.KPI.Management.System.building.entity.BuildingEntity;
import api.v1.KPI.Management.System.building.mapper.BuildingMapper;
import api.v1.KPI.Management.System.building.service.BuildingService;
import api.v1.KPI.Management.System.security.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;

@Service
public class BuildingAdminService extends BuildingService {
    @Autowired
    private BuildingMapper buildingMapper;

    public AppResponse<String> update(BuildingAdminUpdateDTO dto, String lang) {
        return changeDetail(buildingMapper.toUpdatedEntity(dto));
    }

    public BuildingResponseDTO createAdmin(BuildingAdminCreateDTO dto, String lang) {
        BuildingEntity entity = buildingMapper.toCreatedEntity(dto);
        entity.setDepartmentId(SpringSecurityUtil.getCurrentUserDepartmentId());
        return buildingMapper.toResponseDTO(create(entity));
    }

    public AppResponse<String> delete(String id, String lang) {
        BuildingEntity entity = findById(id);
        if (!entity.getDepartmentId().equals(SpringSecurityUtil.getCurrentUserDepartmentId())){
            throw new AuthorizationDeniedException("Access denied");
        }
        return deleteById(id);
    }
}
