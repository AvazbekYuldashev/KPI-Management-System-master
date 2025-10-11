package api.v1.KPI.Management.System.department.service.admin;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.department.dto.admin.DepartmentAdminUpdateDTO;
import api.v1.KPI.Management.System.department.entity.DepartmentEntity;
import api.v1.KPI.Management.System.department.mapper.DepartmentMapper;
import api.v1.KPI.Management.System.department.service.DepartmentService;
import api.v1.KPI.Management.System.security.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;

@Service
public class DepartmentAdminService extends DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    public AppResponse<String> update(DepartmentAdminUpdateDTO dto, String lang) {
        DepartmentEntity entity = findById(dto.getId());
        if (!entity.getChief().equals(SpringSecurityUtil.getCurrentUserId())){
            throw new AuthorizationDeniedException("Access denied");
        }
        return changeDetail(departmentMapper.toUpdatedEntity(dto));
    }
}
