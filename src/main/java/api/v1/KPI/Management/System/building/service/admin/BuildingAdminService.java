package api.v1.KPI.Management.System.building.service.admin;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.app.util.AppResponseUtil;
import api.v1.KPI.Management.System.building.dto.admin.BuildingAdminUpdateDTO;
import api.v1.KPI.Management.System.building.entity.BuildingEntity;
import api.v1.KPI.Management.System.building.mapper.BuildingAdminMapper;
import api.v1.KPI.Management.System.building.service.BuildingService;
import api.v1.KPI.Management.System.security.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;

@Service
public class BuildingAdminService extends BuildingService {
    @Autowired
    private BuildingAdminMapper buildingAdminMapper;

    public AppResponse<String> adminUpdate(BuildingAdminUpdateDTO dto, AppLanguage lang) {
        BuildingEntity building = findById(dto.getId());
        if(!building.getDepartmentId().equals(SpringSecurityUtil.getCurrentUserDepartmentId())) {
            throw new AuthorizationDeniedException("You are not authorized to perform this operation");
        }
        if (!building.getChiefId().equals(SpringSecurityUtil.getCurrentUserId())) {
            throw new AuthorizationDeniedException("You are not authorized to perform this operation");
        }
        return AppResponseUtil.chek(update(buildingAdminMapper.toUpdateEntity(dto), lang));
    }
}
