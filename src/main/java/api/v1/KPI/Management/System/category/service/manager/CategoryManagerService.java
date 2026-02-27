package api.v1.KPI.Management.System.category.service.manager;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.app.util.AppResponseUtil;
import api.v1.KPI.Management.System.category.dto.core.CategoryResponseDTO;
import api.v1.KPI.Management.System.category.dto.manager.CategoryManagerCreateDTO;
import api.v1.KPI.Management.System.category.dto.manager.CategoryManagerUpdateDTO;
import api.v1.KPI.Management.System.category.entity.CategoryEntity;
import api.v1.KPI.Management.System.category.mapper.CategoryManagerMapper;
import api.v1.KPI.Management.System.category.mapper.CategoryMapper;
import api.v1.KPI.Management.System.category.service.CategoryService;
import api.v1.KPI.Management.System.security.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;

@Service
public class CategoryManagerService extends CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private CategoryManagerMapper categoryManagerMapper;

    public CategoryResponseDTO managerCreate(CategoryManagerCreateDTO dto, AppLanguage lang){
        CategoryEntity entity = categoryManagerMapper.toCreatedEntity(dto);
        entity.setDepartmentId(SpringSecurityUtil.getCurrentUserDepartmentId());
        return categoryMapper.toResponseDTO(create(entity));
    }

    public AppResponse<String> managerUpdate(CategoryManagerUpdateDTO dto, AppLanguage lang){
        CategoryEntity category = findByIdAndVisibleTrue(dto.getId());
        if(!category.getDepartmentId().equals(SpringSecurityUtil.getCurrentUserDepartmentId())) {
            throw new AuthorizationDeniedException("You are not authorized to perform this operation");
        }
        CategoryEntity entity = categoryManagerMapper.toUpdatedEntity(dto);
        return AppResponseUtil.chek(update(entity, lang));
    }


}
