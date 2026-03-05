package api.v1.KPI.Management.System.building.service.admin;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.app.service.ResourceBoundleService;
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

    private final BuildingAdminMapper buildingAdminMapper;
    private final ResourceBoundleService boundleService;

    public BuildingAdminService(BuildingAdminMapper buildingAdminMapper,
                                ResourceBoundleService boundleService) {
        this.buildingAdminMapper = buildingAdminMapper;
        this.boundleService = boundleService;
    }
    public AppResponse<String> adminUpdate(BuildingAdminUpdateDTO dto, AppLanguage lang) {
        BuildingEntity building = findById(dto.getId());
        String currentDeptId = SpringSecurityUtil.getCurrentUserDepartmentId();
        String currentUserId = SpringSecurityUtil.getCurrentUserId();

        if (!building.getDepartmentId().equals(currentDeptId) || !building.getChiefId().equals(currentUserId)) {
            throw new AuthorizationDeniedException(
                    boundleService.getMessage("building.update.permission.denied", lang)
            );
        }
        BuildingEntity updatedBuilding = buildingAdminMapper.toUpdateEntity(dto);
        boolean success = update(updatedBuilding, lang);
        String key = success
                ? "building.update.completed.successfully"
                : "building.update.failed";
        return new AppResponse<>(boundleService.getMessage(key, lang));
    }
}
