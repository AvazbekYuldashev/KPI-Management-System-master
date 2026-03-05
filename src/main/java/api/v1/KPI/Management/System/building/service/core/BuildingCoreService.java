package api.v1.KPI.Management.System.building.service.core;

import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.app.service.ResourceBoundleService;
import api.v1.KPI.Management.System.building.dto.core.BuildingResponseDTO;
import api.v1.KPI.Management.System.building.entity.BuildingEntity;
import api.v1.KPI.Management.System.building.mapper.BuildingMapper;
import api.v1.KPI.Management.System.building.service.BuildingService;
import api.v1.KPI.Management.System.department.service.helper.DepartmentHelperService;
import api.v1.KPI.Management.System.exception.exps.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
public class BuildingCoreService extends BuildingService {

    private final BuildingMapper buildingMapper;
    private final DepartmentHelperService departmentHelperService;
    private final ResourceBoundleService boundleService;

    public BuildingCoreService(BuildingMapper buildingMapper,
                               DepartmentHelperService departmentHelperService,
                               ResourceBoundleService boundleService) {
        this.buildingMapper = buildingMapper;
        this.departmentHelperService = departmentHelperService;
        this.boundleService = boundleService;
    }

    public BuildingResponseDTO getById(String id, AppLanguage lang){
        BuildingEntity entity = findById(id);
        if (entity != null) return buildingMapper.toResponseDTO(entity);
        throw new ResourceNotFoundException(boundleService.getMessage("building.not.found", lang));
    }

    public Page<BuildingResponseDTO> getAllPage(int page, int size, AppLanguage lang) {
        return findAllPageAndVisibleTrue(PageRequest.of(page, size))
                .map(buildingMapper::toResponseDTO);
    }

    public Page<BuildingResponseDTO> getByDepartmentId(String id, int page, int size, AppLanguage lang) {
        departmentHelperService.findById(id, lang);
        return findAllByDepartmentIdAndVisibleTruePage(id, PageRequest.of(page, size))
                .map(buildingMapper::toResponseDTO);
    }
}

