package api.v1.KPI.Management.System.category.service.admin;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.category.dto.admin.CategoryAdminUpdateDTO;
import api.v1.KPI.Management.System.category.entity.CategoryEntity;
import api.v1.KPI.Management.System.category.mapper.CategoryMapper;
import api.v1.KPI.Management.System.category.service.CategoryService;
import api.v1.KPI.Management.System.security.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;

@Service
public class CategoryAdminService extends CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    public AppResponse<String> update(CategoryAdminUpdateDTO dto, String lang) {
        CategoryEntity entity = findById(dto.getId());
        if (!entity.getDepartmentId().equals(SpringSecurityUtil.getCurrentUserDepartmentId())){
            throw new AuthorizationDeniedException("Access denied");
        }
        return changeDetail(categoryMapper.toUpdatedEntity(dto));
    }
}
