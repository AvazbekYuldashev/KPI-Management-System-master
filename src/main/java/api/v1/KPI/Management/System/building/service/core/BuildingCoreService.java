package api.v1.KPI.Management.System.building.service.core;

import api.v1.KPI.Management.System.building.dto.BuildingResponseDTO;
import api.v1.KPI.Management.System.building.mapper.BuildingMapper;
import api.v1.KPI.Management.System.building.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

@Service
public class BuildingCoreService extends BuildingService {
    @Autowired
    private BuildingMapper buildingMapper;
    public BuildingResponseDTO userGetById(String id) {
        return buildingMapper.toResponseDTO(findById(id));
    }

    public PageImpl<BuildingResponseDTO> getAll(int page, int size) {
        return findAll(page, size);
    }

    public PageImpl<BuildingResponseDTO> getByDepartmentId(int page, int size, String id) {
        return findByDepartmentId(page, size, id);
    }
}
