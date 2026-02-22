package api.v1.KPI.Management.System.building.service.manager;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.app.util.AppResponseUtil;
import api.v1.KPI.Management.System.building.dto.core.BuildingResponseDTO;
import api.v1.KPI.Management.System.building.dto.manager.BuildingManagerCreateDTO;
import api.v1.KPI.Management.System.building.dto.manager.BuildingManagerUpdateDTO;
import api.v1.KPI.Management.System.building.entity.BuildingEntity;
import api.v1.KPI.Management.System.building.mapper.BuildingManagerMapper;
import api.v1.KPI.Management.System.building.mapper.BuildingMapper;
import api.v1.KPI.Management.System.building.service.BuildingService;
import api.v1.KPI.Management.System.profile.service.core.ProfileService;
import api.v1.KPI.Management.System.security.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingManagerService extends BuildingService {
    @Autowired
    private BuildingMapper buildingMapper;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private BuildingManagerMapper buildingManagerMapper;

    public Page<BuildingResponseDTO> getAllPage(int page, Integer size, AppLanguage lang) {
        Pageable pageable = PageRequest.of(page, size);
        return findAllByDepartmentIdAndVisibleTruePage(SpringSecurityUtil.getCurrentUserDepartmentId(), pageable).map(entity -> buildingMapper.toResponseDTO(entity));
    }

    public BuildingResponseDTO managerCreate(BuildingManagerCreateDTO dto, AppLanguage lang) {
        BuildingEntity entity = buildingManagerMapper.toCreatedEntity(dto);
        entity.setDepartmentId(SpringSecurityUtil.getCurrentUserDepartmentId());
        if (dto.getChiefId() != null) {
            entity.setChiefId(dto.getChiefId());
            profileService.employeeUpdate(dto.getChiefId(), findById(dto.getChiefId()).getDepartmentId(), true);
        }
        return buildingMapper.toResponseDTO(create(entity));
    }

    public AppResponse<String> managerUpdate(BuildingManagerUpdateDTO dto, AppLanguage lang) {
        BuildingEntity building = findById(dto.getId());
        if(!building.getDepartmentId().equals(SpringSecurityUtil.getCurrentUserDepartmentId())) {
            throw new AuthorizationDeniedException("You are not authorized to perform this operation");
        }
        BuildingEntity entity = buildingManagerMapper.toUpdatedEntity(dto);
        entity.setDepartmentId(SpringSecurityUtil.getCurrentUserDepartmentId());
        if (dto.getChiefId() != null) {
            entity.setChiefId(dto.getChiefId());
            profileService.employeeUpdate(dto.getChiefId(), findById(dto.getId()).getDepartmentId(), true);
        }
        return AppResponseUtil.chek(update(entity, lang));
    }
}
