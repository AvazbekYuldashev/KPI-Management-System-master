package api.v1.KPI.Management.System.category.service;

import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.building.entity.BuildingEntity;
import api.v1.KPI.Management.System.category.entity.CategoryEntity;
import api.v1.KPI.Management.System.category.repository.CategoryRepository;
import api.v1.KPI.Management.System.exception.exps.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryEntity create(CategoryEntity entity){return categoryRepository.save(entity);}

    public CategoryEntity findByIdAndVisibleTrue(String id){return categoryRepository.findByIdAndVisibleTrue(id).get();}

    public Page<CategoryEntity> findAllByVisibleTruePage(Pageable pageable) {return categoryRepository.findAllByVisibleTruePage(pageable);}

    public Page<CategoryEntity> findAllByDepartmentIdAndVisibleTruePage(String id, Pageable pageable) {return categoryRepository.findAllByDepartmentIdAndVisibleTruePage(id, pageable);}

    public Page<CategoryEntity> findByBuildingIdAndVisibleTruePage(Pageable pageable, String buildingId) {return categoryRepository.findByBuildingIdPage(buildingId, pageable);}

    public Boolean update(CategoryEntity entity, AppLanguage lang){
        CategoryEntity category = findByIdAndVisibleTrue(entity.getId());
        if (category == null) throw new ResourceNotFoundException("Category Not Found");
        return categoryRepository.update(entity) > 0;
    }
}
