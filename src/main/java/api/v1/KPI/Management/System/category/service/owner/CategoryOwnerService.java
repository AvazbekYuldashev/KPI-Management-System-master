package api.v1.KPI.Management.System.category.service.owner;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.category.dto.core.CategoryResponseDTO;
import api.v1.KPI.Management.System.category.dto.owner.CategoryOwnerCreateDTO;
import api.v1.KPI.Management.System.category.dto.owner.CategoryOwnerUpdateDTO;
import api.v1.KPI.Management.System.category.mapper.CategoryMapper;
import api.v1.KPI.Management.System.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryOwnerService extends CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;


    public CategoryResponseDTO createOwner(CategoryOwnerCreateDTO dto, String lang) {
        return categoryMapper.toResponseDTO(create(categoryMapper.toCreatedEntity(dto)));
    }

    public AppResponse<String> update(CategoryOwnerUpdateDTO dto, String lang) {
        return changeDetail(categoryMapper.toUpdatedEntity(dto));
    }

    public AppResponse<String> delete(String id, String lang) {
        return deleteById(id);
    }
}
