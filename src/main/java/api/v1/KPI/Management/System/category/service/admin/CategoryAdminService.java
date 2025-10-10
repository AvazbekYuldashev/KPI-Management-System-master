package api.v1.KPI.Management.System.category.service.admin;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.category.dto.CategoryResponseDTO;
import api.v1.KPI.Management.System.category.dto.admin.CategoryAdminCreateDTO;
import api.v1.KPI.Management.System.category.dto.admin.CategoryAdminUpdateDTO;
import api.v1.KPI.Management.System.category.mapper.CategoryMapper;
import api.v1.KPI.Management.System.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

@Service
public class CategoryAdminService extends CategoryService{
    @Autowired
    private CategoryMapper categoryMapper;


    public CategoryResponseDTO adminCreate(CategoryAdminCreateDTO dto) {
        return categoryMapper.toResponseDTO(create(categoryMapper.toAdminCreateEntity(dto)));
    }

    public PageImpl<CategoryResponseDTO> adminGetAll(int page, int size) {
        return getAll(page, size);
    }

    public AppResponse<String> adminDelete(String id) {
        return deleteById(id);
    }

    public AppResponse<String> adminUpdate(String id, CategoryAdminUpdateDTO dto) {
        return updateDetail(id, dto);
    }
}
