package api.v1.KPI.Management.System.category.service;

import api.v1.KPI.Management.System.app.enums.AppLanguage;
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

    public CategoryEntity create(CategoryEntity entity){
        return categoryRepository.save(entity);
    }

    public CategoryEntity findById(String id){
        Optional<CategoryEntity> optional = categoryRepository.findByIdAndVisibleTrue(id);
        return optional.get();
    }
    public CategoryEntity findByTitle(String title){
        Optional<CategoryEntity> optional = categoryRepository.findByTitleAndVisibleTrue(title);
        return optional.get();
    }
    public Boolean update(CategoryEntity entity, AppLanguage lang){
        CategoryEntity category = findById(entity.getId());
        if (category == null) throw new ResourceNotFoundException("Category Not Found");
        return categoryRepository.update(entity) > 0;
    }


    public Page<CategoryEntity> findByDepartmentId(int page, int size, String departmentId) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        return categoryRepository.findAllByDepartmentId(departmentId, pageable);
    }

    public Page<CategoryEntity> findByBuildingId(int page, int size, String buildingId) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        return categoryRepository.findAllByBuildingId(buildingId, pageable);
    }
}
