package api.v1.KPI.Management.System.department.service.manager;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.app.util.AppResponseUtil;
import api.v1.KPI.Management.System.department.dto.core.DepartmentResponseDTO;
import api.v1.KPI.Management.System.department.dto.manager.DepartmentManagerUpdateDTO;
import api.v1.KPI.Management.System.department.entity.DepartmentEntity;
import api.v1.KPI.Management.System.department.mapper.DepartmentManagerMapper;
import api.v1.KPI.Management.System.department.mapper.DepartmentMapper;
import api.v1.KPI.Management.System.department.service.DepartmentService;
import api.v1.KPI.Management.System.exception.exps.ResourceNotFoundException;
import api.v1.KPI.Management.System.security.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;

@Service
public class DepartmentManagerService extends DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private DepartmentManagerMapper departmentManagerMapper;

    public AppResponse<String> changeDetail(DepartmentManagerUpdateDTO dto, AppLanguage lang) {
        DepartmentEntity entity = findById(SpringSecurityUtil.getCurrentUserDepartmentId());
        if (entity == null) {throw new ResourceNotFoundException("Department not found");}
        if (!entity.getId().equals(SpringSecurityUtil.getCurrentUserDepartmentId())) {
            throw new AuthorizationDeniedException("You are not authorized to perform this operation");
        }
        DepartmentEntity department = departmentManagerMapper.toUpdatedEntity(dto);
        return AppResponseUtil.chek(update(department, lang));
    }

    public DepartmentResponseDTO getMyDepartment(AppLanguage lang) {
        DepartmentEntity entity = findById(SpringSecurityUtil.getCurrentUserDepartmentId());
        if (entity == null) {throw new ResourceNotFoundException("Department not found");}
        return departmentMapper.toResponseDTO(entity);
    }
}
