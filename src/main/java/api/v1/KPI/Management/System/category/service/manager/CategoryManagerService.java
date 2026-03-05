package api.v1.KPI.Management.System.category.service.manager;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.app.service.ResourceBoundleService;
import api.v1.KPI.Management.System.app.util.AppResponseUtil;
import api.v1.KPI.Management.System.category.dto.core.CategoryResponseDTO;
import api.v1.KPI.Management.System.category.dto.manager.CategoryManagerCreateDTO;
import api.v1.KPI.Management.System.category.dto.manager.CategoryManagerUpdateDTO;
import api.v1.KPI.Management.System.category.entity.CategoryEntity;
import api.v1.KPI.Management.System.category.mapper.CategoryManagerMapper;
import api.v1.KPI.Management.System.category.mapper.CategoryMapper;
import api.v1.KPI.Management.System.category.service.CategoryService;
import api.v1.KPI.Management.System.security.util.SpringSecurityUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;

@Service
public class CategoryManagerService extends CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoryManagerMapper categoryManagerMapper;
    private final ResourceBoundleService boundleService;

    public CategoryManagerService(CategoryMapper categoryMapper,
                                  CategoryManagerMapper categoryManagerMapper,
                                  ResourceBoundleService boundleService) {
        this.categoryMapper = categoryMapper;
        this.categoryManagerMapper = categoryManagerMapper;
        this.boundleService = boundleService;
    }

    @Transactional
    public CategoryResponseDTO managerCreate(CategoryManagerCreateDTO dto, AppLanguage lang){
        CategoryEntity entity = categoryManagerMapper.toCreatedEntity(dto);
        entity.setDepartmentId(SpringSecurityUtil.getCurrentUserDepartmentId());
        return categoryMapper.toResponseDTO(create(entity));
    }

    @Transactional
    public AppResponse<String> managerUpdate(CategoryManagerUpdateDTO dto, AppLanguage lang){
        String departmentId = SpringSecurityUtil.getCurrentUserDepartmentId();
        CategoryEntity category = findByIdAndVisibleTrue(dto.getId());

        if(!departmentId.equals(category.getDepartmentId())) {
            throw new AuthorizationDeniedException(
                    boundleService.getMessage("category.update.permission.denied", lang)
            );
        }

        CategoryEntity entity = categoryManagerMapper.toUpdatedEntity(dto);
        entity.setDepartmentId(departmentId);

        boolean success = update(entity, lang);
        String messageKey = success ? "category.update.completed.successfully" : "category.update.failed";

        return new AppResponse<>(boundleService.getMessage(messageKey, lang));
    }


}
