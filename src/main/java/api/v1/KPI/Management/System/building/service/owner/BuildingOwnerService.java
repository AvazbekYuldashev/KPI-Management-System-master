package api.v1.KPI.Management.System.building.service.owner;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.app.util.AppResponseUtil;
import api.v1.KPI.Management.System.building.dto.owner.BuildingOwnerCreateDTO;
import api.v1.KPI.Management.System.building.dto.core.BuildingResponseDTO;
import api.v1.KPI.Management.System.building.dto.owner.BuildingOwnerUpdateDTO;
import api.v1.KPI.Management.System.building.entity.BuildingEntity;
import api.v1.KPI.Management.System.building.mapper.BuildingMapper;
import api.v1.KPI.Management.System.building.service.BuildingService;
import api.v1.KPI.Management.System.profile.service.owner.ProfileOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingOwnerService extends BuildingService {
    @Autowired
    private BuildingMapper buildingMapper;
    @Autowired
    private ProfileOwnerService profileService;

    public BuildingResponseDTO ownerCreate(BuildingOwnerCreateDTO dto, AppLanguage lang){
        BuildingEntity entity = buildingMapper.toCreatedOwnerEntity(dto);
        if (dto.getChiefId() != null) {
            entity.setChiefId(dto.getChiefId());
            profileService.updateEmployee(dto.getChiefId(), entity.getDepartmentId(), lang);
        }
        return buildingMapper.toResponseDTO(create(entity));
    }

    public AppResponse<String> ownerUpdate(BuildingOwnerUpdateDTO dto, AppLanguage lang){
        BuildingEntity entity = buildingMapper.toUpdatedOwnerEntity(dto);
        if (dto.getChiefId() != null) {
            entity.setChiefId(dto.getChiefId());
            profileService.updateEmployee(dto.getChiefId(), findById(dto.getId()).getDepartmentId(), lang);
        }
        return AppResponseUtil.chek(update(entity, lang));
    }

    public Page<BuildingResponseDTO> getAllPage(int page, Integer size, AppLanguage lang) {
        Pageable pageable = PageRequest.of(page, size);

        // Bazadan sahifa bo‘yicha ma'lumotlarni olish
        Page<BuildingEntity> entitiesPage = findAllPage(pageable);

        // Entity → DTO map qilish
        List<BuildingResponseDTO> dtoList = entitiesPage.getContent().stream()
                .map(entity -> {return buildingMapper.toResponseDTO(entity);}).toList();

        // PageImpl orqali sahifa va pagination ma’lumotlarini saqlab DTO qaytarish
        return new PageImpl<>(dtoList, pageable, entitiesPage.getTotalElements());
    }
}
