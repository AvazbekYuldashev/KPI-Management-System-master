package api.v1.KPI.Management.System.building.service;

import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.building.entity.BuildingEntity;
import api.v1.KPI.Management.System.building.repoisotry.BuildingRepository;
import api.v1.KPI.Management.System.exception.exps.ResourceNotFoundException;
import api.v1.KPI.Management.System.profile.repository.ProfileRepository;
import api.v1.KPI.Management.System.profile.service.core.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;

    public BuildingEntity create(BuildingEntity entity){
        return buildingRepository.save(entity);
    }

    public BuildingEntity findById(String id){
        Optional<BuildingEntity> optional = buildingRepository.findByIdAndVisibleTrue(id);
        return optional.get();
    }
    public BuildingEntity findByTitle(String title){
        Optional<BuildingEntity> optional = buildingRepository.findByTitleAndVisibleTrue(title);
        return optional.get();
    }
    public Boolean update(BuildingEntity entity, AppLanguage lang){
        BuildingEntity department = findById(entity.getId());
        if (department == null) throw new ResourceNotFoundException("Department Not Found");
        int effectedRow = buildingRepository.update(entity);
        return effectedRow > 0;
    }

    public Page<BuildingEntity> findAllPageAndVisibleTrue(Pageable pageable) {
        return buildingRepository.findAllPageAndVisibleTrue(pageable);
    }

    public Page<BuildingEntity> findAllPage(Pageable pageable) {
        return buildingRepository.findAllPage(pageable);
    }
}
