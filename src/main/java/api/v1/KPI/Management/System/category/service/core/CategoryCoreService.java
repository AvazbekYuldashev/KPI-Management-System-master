package api.v1.KPI.Management.System.category.service.core;

import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.category.dto.core.CategoryResponseDTO;
import api.v1.KPI.Management.System.category.entity.CategoryEntity;
import api.v1.KPI.Management.System.category.mapper.CategoryMapper;
import api.v1.KPI.Management.System.category.service.CategoryService;
import api.v1.KPI.Management.System.exception.exps.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class CategoryCoreService extends CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    public CategoryResponseDTO getById(String id, AppLanguage lang){
        CategoryEntity entity = findById(id);
        if (entity != null) return categoryMapper.toResponseDTO(entity);
        throw new ResourceNotFoundException("Category Not Found");
    }

    public CategoryResponseDTO getByTitle(String title, AppLanguage lang) {
        CategoryEntity entity = findByTitle(title);
        if (entity != null) return categoryMapper.toResponseDTO(entity);
        throw new ResourceNotFoundException("Category Not Found");
    }

    public Page<CategoryResponseDTO> getByDepartmentId(String id, int page, int size, AppLanguage lang) {
        return findByDepartmentId(page, size, id).map(entity -> categoryMapper.toResponseDTO(entity));

    }

    public Page<CategoryResponseDTO> getByBuildingId(String id, int page, int size, AppLanguage lang) {
        return findByBuildingId(page, size, id).map(entity -> categoryMapper.toResponseDTO(entity));
    }
}
