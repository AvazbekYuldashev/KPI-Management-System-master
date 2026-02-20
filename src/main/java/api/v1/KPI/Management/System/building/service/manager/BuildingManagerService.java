package api.v1.KPI.Management.System.building.service.manager;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.app.util.AppResponseUtil;
import api.v1.KPI.Management.System.building.dto.core.BuildingResponseDTO;
import api.v1.KPI.Management.System.building.dto.manager.BuildingManagerCreateDTO;
import api.v1.KPI.Management.System.building.dto.manager.BuildingManagerUpdateDTO;
import api.v1.KPI.Management.System.building.dto.owner.BuildingOwnerUpdateDTO;
import api.v1.KPI.Management.System.building.entity.BuildingEntity;
import api.v1.KPI.Management.System.building.mapper.BuildingMapper;
import api.v1.KPI.Management.System.building.service.BuildingService;
import api.v1.KPI.Management.System.profile.service.admin.ProfileAdminService;
import api.v1.KPI.Management.System.profile.service.core.ProfileService;
import api.v1.KPI.Management.System.security.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingManagerService extends BuildingService {
    @Autowired
    private BuildingMapper buildingMapper;
    @Autowired
    private ProfileService profileService;

    public Page<BuildingResponseDTO> getAllPage(int page, Integer size, AppLanguage lang) {
        Pageable pageable = PageRequest.of(page, size);

        // Bazadan sahifa bo‘yicha ma'lumotlarni olish
        Page<BuildingEntity> entitiesPage = findAllPageByDepartmentId(SpringSecurityUtil.getCurrentUserDepartmentId(), pageable);

        // Entity → DTO map qilish
        List<BuildingResponseDTO> dtoList = entitiesPage.getContent().stream()
                .map(entity -> {return buildingMapper.toResponseDTO(entity);}).toList();

        // PageImpl orqali sahifa va pagination ma’lumotlarini saqlab DTO qaytarish
        return new PageImpl<>(dtoList, pageable, entitiesPage.getTotalElements());
    }

    public BuildingResponseDTO managerCreate(BuildingManagerCreateDTO dto, AppLanguage lang) {
        BuildingEntity entity = buildingMapper.toCreatedManagerEntity(dto);
        entity.setDepartmentId(SpringSecurityUtil.getCurrentUserDepartmentId());
        if (dto.getChiefId() != null) {
            entity.setChiefId(dto.getChiefId());
            profileService.employeeUpdate(dto.getChiefId(), findById(dto.getChiefId()).getDepartmentId(), true);
        }
        return buildingMapper.toResponseDTO(create(entity));
    }

    public AppResponse<String> managerUpdate(BuildingManagerUpdateDTO dto, AppLanguage lang) {
        BuildingEntity entity = buildingMapper.toUpdatedManagerEntity(dto);
        entity.setDepartmentId(SpringSecurityUtil.getCurrentUserDepartmentId());
        if (dto.getChiefId() != null) {
            entity.setChiefId(dto.getChiefId());
            profileService.employeeUpdate(dto.getChiefId(), findById(dto.getId()).getDepartmentId(), true);
        }
        return AppResponseUtil.chek(update(entity, lang));
    }
}
