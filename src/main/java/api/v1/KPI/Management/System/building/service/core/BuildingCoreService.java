package api.v1.KPI.Management.System.building.service.core;

import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.building.dto.core.BuildingResponseDTO;
import api.v1.KPI.Management.System.building.mapper.BuildingMapper;
import api.v1.KPI.Management.System.building.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class BuildingCoreService extends BuildingService {
    @Autowired
    private BuildingMapper buildingMapper;

    public BuildingResponseDTO getById(String id, AppLanguage lang) {
        return buildingMapper.toResponseDTO(findById(id));
    }

    public Page<BuildingResponseDTO> getAll(int page, Integer size, AppLanguage lang) {
        return findAll(page, size);
    }

    public Page<BuildingResponseDTO> getByDepartmentId(String id, int page, Integer size, AppLanguage lang) {
        return findByDepartmentId(id, page, size);
    }
}
