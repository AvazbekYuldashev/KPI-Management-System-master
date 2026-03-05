package api.v1.KPI.Management.System.building.service.manager;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.app.service.ResourceBoundleService;
import api.v1.KPI.Management.System.building.dto.core.BuildingResponseDTO;
import api.v1.KPI.Management.System.building.dto.manager.BuildingManagerCreateDTO;
import api.v1.KPI.Management.System.building.dto.manager.BuildingManagerUpdateDTO;
import api.v1.KPI.Management.System.building.entity.BuildingEntity;
import api.v1.KPI.Management.System.building.mapper.BuildingManagerMapper;
import api.v1.KPI.Management.System.building.mapper.BuildingMapper;
import api.v1.KPI.Management.System.building.service.BuildingService;
import api.v1.KPI.Management.System.profile.service.helper.ProfileHelperService;
import api.v1.KPI.Management.System.security.util.SpringSecurityUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;


@Service
public class BuildingManagerService extends BuildingService {

    private final BuildingMapper buildingMapper;
    private final BuildingManagerMapper buildingManagerMapper;
    private final ProfileHelperService profileHelperService;
    private final ResourceBoundleService boundleService;

    public BuildingManagerService(BuildingMapper buildingMapper,
                                  BuildingManagerMapper buildingManagerMapper,
                                  ProfileHelperService profileHelperService,
                                  ResourceBoundleService boundleService) {
        this.buildingMapper = buildingMapper;
        this.buildingManagerMapper = buildingManagerMapper;
        this.profileHelperService = profileHelperService;
        this.boundleService = boundleService;
    }
    @Transactional
    public BuildingResponseDTO managerCreate(BuildingManagerCreateDTO dto, AppLanguage lang) {
        String departmentId = SpringSecurityUtil.getCurrentUserDepartmentId();
        String chiefId = dto.getChiefId();

        BuildingEntity entity = buildingManagerMapper.toCreatedEntity(dto);
        entity.setDepartmentId(departmentId);
        if (chiefId != null) entity.setChiefId(chiefId);

        BuildingEntity building = create(entity);

        if (chiefId != null) {
            profileHelperService.changeBuilding(chiefId, departmentId, building.getId(), lang);
        }

        return buildingMapper.toResponseDTO(building);
    }

    @Transactional
    public AppResponse<String> managerUpdate(BuildingManagerUpdateDTO dto, AppLanguage lang) {
        String departmentId = SpringSecurityUtil.getCurrentUserDepartmentId();
        String chiefId = dto.getChiefId();

        BuildingEntity building = findById(dto.getId());
        if (!building.getDepartmentId().equals(departmentId)) {
            throw new AuthorizationDeniedException(
                    boundleService.getMessage("building.update.permission.denied", lang)
            );
        }
        BuildingEntity entity = buildingManagerMapper.toUpdatedEntity(dto);
        entity.setDepartmentId(departmentId);
        if (chiefId != null) {
            entity.setChiefId(chiefId);
            profileHelperService.changeBuilding(chiefId, departmentId, building.getId(), lang);
        }
        boolean success = update(entity, lang);
        String messageKey = success ? "building.update.completed.successfully" : "building.update.failed";

        return new AppResponse<>(boundleService.getMessage(messageKey, lang));
    }
}
