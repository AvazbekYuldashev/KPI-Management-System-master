package api.v1.KPI.Management.System.category.service.owner;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.app.util.AppResponseUtil;
import api.v1.KPI.Management.System.category.dto.core.CategoryResponseDTO;
import api.v1.KPI.Management.System.category.dto.owner.CategoryCreateDTO;
import api.v1.KPI.Management.System.category.dto.owner.CategoryUpdateDTO;
import api.v1.KPI.Management.System.category.entity.CategoryEntity;
import api.v1.KPI.Management.System.category.mapper.CategoryMapper;
import api.v1.KPI.Management.System.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryOwnerService extends CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    public CategoryResponseDTO ownerCreate(CategoryCreateDTO dto, AppLanguage lang){
        CategoryEntity entity = categoryMapper.toCreatedEntity(dto);
        return categoryMapper.toResponseDTO(create(entity));
    }

    public AppResponse<String> ownerUpdate(CategoryUpdateDTO dto, AppLanguage lang){
        CategoryEntity entity = categoryMapper.toUpdatedEntity(dto);
        return AppResponseUtil.chek(update(entity, lang));
    }

}
