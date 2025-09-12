package api.v1.KPI.Management.System.category.service.user;

import api.v1.KPI.Management.System.category.dto.CategoryResponseDTO;
import api.v1.KPI.Management.System.category.mapper.CategoryMapper;
import api.v1.KPI.Management.System.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

@Service
public class CategoryUserService extends CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    public CategoryResponseDTO userGetById(String id) {
        return categoryMapper.toResponseDTO(findById(id));
    }

    public PageImpl<CategoryResponseDTO> userGetAll(int page, int size) {
        return getAll(page, size);
    }
}
