package api.v1.KPI.Management.System.category.service.core;

import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.category.dto.core.CategoryResponseDTO;
import api.v1.KPI.Management.System.category.mapper.CategoryMapper;
import api.v1.KPI.Management.System.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class CategoryCoreService extends CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    public CategoryResponseDTO getById(String id, AppLanguage lang) {
        return categoryMapper.toResponseDTO(findById(id));
    }

    public Page<CategoryResponseDTO> getAll(int page, Integer size, AppLanguage lang) {
        return findAll(page, size);
    }

    public Page<CategoryResponseDTO> getByDepartmentId(String id, int page, Integer size, AppLanguage lang) {
        return findByDepartmentId(id, page, size);
    }
}
