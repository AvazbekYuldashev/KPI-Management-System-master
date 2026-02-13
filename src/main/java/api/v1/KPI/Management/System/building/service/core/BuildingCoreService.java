package api.v1.KPI.Management.System.building.service.core;

import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.building.dto.core.BuildingResponseDTO;
import api.v1.KPI.Management.System.building.entity.BuildingEntity;
import api.v1.KPI.Management.System.building.mapper.BuildingMapper;
import api.v1.KPI.Management.System.building.service.BuildingService;
import api.v1.KPI.Management.System.exception.exps.ResourceNotFoundException;
import api.v1.KPI.Management.System.profile.dto.profile.ProfileResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Page<BuildingResponseDTO> getAllPage(int page, Integer size, AppLanguage lang) {
        Pageable pageable = PageRequest.of(page, size);

        // Bazadan sahifa bo‘yicha ma'lumotlarni olish
        Page<BuildingEntity> entitiesPage = findAllPageAndVisibleTrue(pageable);

        // Entity → DTO map qilish
        List<BuildingResponseDTO> dtoList = entitiesPage.getContent().stream()
                .map(entity -> {return buildingMapper.toResponseDTO(entity);}).toList();

        // PageImpl orqali sahifa va pagination ma’lumotlarini saqlab DTO qaytarish
        return new PageImpl<>(dtoList, pageable, entitiesPage.getTotalElements());
    }

}

