package api.v1.KPI.Management.System.building.service.owner;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.app.util.AppResponseUtil;
import api.v1.KPI.Management.System.building.dto.owner.BuildingOwnerCreateDTO;
import api.v1.KPI.Management.System.building.dto.core.BuildingResponseDTO;
import api.v1.KPI.Management.System.building.dto.owner.BuildingOwnerUpdateDTO;
import api.v1.KPI.Management.System.building.entity.BuildingEntity;
import api.v1.KPI.Management.System.building.mapper.BuildingMapper;
import api.v1.KPI.Management.System.building.mapper.BuildingOwnerMapper;
import api.v1.KPI.Management.System.building.service.BuildingService;
import api.v1.KPI.Management.System.profile.service.helper.ProfileHelperService;
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
    private BuildingOwnerMapper buildingOwnerMapper;
    @Autowired
    private ProfileHelperService profileHelperService;


    public BuildingResponseDTO ownerCreate(BuildingOwnerCreateDTO dto, AppLanguage lang){
        BuildingEntity entity = buildingOwnerMapper.toCreatedEntity(dto);
        if (dto.getChiefId() != null) {
            entity.setChiefId(dto.getChiefId());
        }
        BuildingEntity response = create(entity);
        profileHelperService.changeBuilding(dto.getChiefId(), response.getDepartmentId(), response.getId(), lang);

        return buildingMapper.toResponseDTO(response);
    }

    public AppResponse<String> ownerUpdate(BuildingOwnerUpdateDTO dto, AppLanguage lang){
        BuildingEntity building = findById(dto.getId());
        BuildingEntity entity = buildingOwnerMapper.toUpdatedEntity(dto);

        if (dto.getChiefId() != null) {
            entity.setChiefId(dto.getChiefId());
            profileHelperService.changeBuilding(dto.getChiefId(), building.getDepartmentId(), building.getId(), lang);
        }
        return AppResponseUtil.chek(update(entity, lang));
    }

    public Page<BuildingResponseDTO> getAllPage(int page, Integer size, AppLanguage lang) {
        Pageable pageable = PageRequest.of(page, size);
        return findAllPage(pageable).map(entity -> buildingMapper.toResponseDTO(entity));
    }
}
