package api.v1.KPI.Management.System.building.service.owner;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.app.service.ResourceBoundleService;
import api.v1.KPI.Management.System.building.dto.owner.BuildingOwnerCreateDTO;
import api.v1.KPI.Management.System.building.dto.core.BuildingResponseDTO;
import api.v1.KPI.Management.System.building.dto.owner.BuildingOwnerUpdateDTO;
import api.v1.KPI.Management.System.building.entity.BuildingEntity;
import api.v1.KPI.Management.System.building.mapper.BuildingMapper;
import api.v1.KPI.Management.System.building.mapper.BuildingOwnerMapper;
import api.v1.KPI.Management.System.building.service.BuildingService;
import api.v1.KPI.Management.System.profile.service.helper.ProfileHelperService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BuildingOwnerService extends BuildingService {

    private final BuildingMapper buildingMapper;
    private final BuildingOwnerMapper buildingOwnerMapper;
    private final ProfileHelperService profileHelperService;
    private final ResourceBoundleService boundleService;

    public BuildingOwnerService(BuildingMapper buildingMapper,
                                BuildingOwnerMapper buildingOwnerMapper,
                                ProfileHelperService profileHelperService,
                                ResourceBoundleService boundleService) {
        this.buildingMapper = buildingMapper;
        this.buildingOwnerMapper = buildingOwnerMapper;
        this.profileHelperService = profileHelperService;
        this.boundleService = boundleService;
    }

    @Transactional
    public BuildingResponseDTO ownerCreate(BuildingOwnerCreateDTO dto, AppLanguage lang){
        BuildingEntity entity = buildingOwnerMapper.toCreatedEntity(dto);
        if (dto.getChiefId() != null) {
            entity.setChiefId(dto.getChiefId());
        }
        BuildingEntity building = create(entity);
        if (dto.getChiefId() != null) {
            profileHelperService.changeBuilding(dto.getChiefId(), building.getDepartmentId(), building.getId(), lang);
        }
        return buildingMapper.toResponseDTO(building);
    }

    @Transactional
    public AppResponse<String> ownerUpdate(BuildingOwnerUpdateDTO dto, AppLanguage lang){
        BuildingEntity building = findById(dto.getId());
        BuildingEntity entity = buildingOwnerMapper.toUpdatedEntity(dto);

        String newChiefId = dto.getChiefId();
        String oldChiefId = building.getChiefId();

        if (newChiefId != null && !newChiefId.equals(oldChiefId)) {
            entity.setChiefId(newChiefId);
            profileHelperService.changeBuilding(newChiefId, building.getDepartmentId(), building.getId(), lang);
        }

        boolean success = update(entity, lang);
        String messageKey = success ? "building.update.completed.successfully" : "building.update.failed";

        return new AppResponse<>(boundleService.getMessage(messageKey, lang));
    }

    public Page<BuildingResponseDTO> getAllPage(int page, Integer size, AppLanguage lang) {
        Pageable pageable = PageRequest.of(page, size);
        return findAllPage(pageable).map(buildingMapper::toResponseDTO);
    }
}
