package api.v1.KPI.Management.System.building.service.core;

import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.building.dto.core.BuildingResponseDTO;
import api.v1.KPI.Management.System.building.entity.BuildingEntity;
import api.v1.KPI.Management.System.building.mapper.BuildingMapper;
import api.v1.KPI.Management.System.building.service.BuildingService;
import api.v1.KPI.Management.System.exception.exps.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildingCoreService extends BuildingService {
    @Autowired
    private BuildingMapper buildingMapper;

    public BuildingResponseDTO getById(String id, AppLanguage lang){
        BuildingEntity entity = findById(id);
        if (entity != null) return buildingMapper.toResponseDTO(entity);
        throw new ResourceNotFoundException("Building Not Found");

    }


    public BuildingResponseDTO getByTitle(String title, AppLanguage lang) {
        BuildingEntity entity = findByTitle(title);
        if (entity != null) return buildingMapper.toResponseDTO(entity);
        throw new ResourceNotFoundException("Building Not Found");
    }

}

