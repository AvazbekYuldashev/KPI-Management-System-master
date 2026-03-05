package api.v1.KPI.Management.System.department.service.manager;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.app.service.ResourceBoundleService;
import api.v1.KPI.Management.System.app.util.AppResponseUtil;
import api.v1.KPI.Management.System.department.dto.core.DepartmentResponseDTO;
import api.v1.KPI.Management.System.department.dto.manager.DepartmentManagerUpdateDTO;
import api.v1.KPI.Management.System.department.entity.DepartmentEntity;
import api.v1.KPI.Management.System.department.mapper.DepartmentManagerMapper;
import api.v1.KPI.Management.System.department.mapper.DepartmentMapper;
import api.v1.KPI.Management.System.department.service.DepartmentService;
import api.v1.KPI.Management.System.exception.exps.ResourceNotFoundException;
import api.v1.KPI.Management.System.security.util.SpringSecurityUtil;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;

@Service
public class DepartmentManagerService extends DepartmentService {

    private final DepartmentMapper departmentMapper;
    private final DepartmentManagerMapper departmentManagerMapper;
    private final ResourceBoundleService boundleService;

    public DepartmentManagerService(DepartmentMapper departmentMapper,
                                    DepartmentManagerMapper departmentManagerMapper,
                                    ResourceBoundleService boundleService) {
        this.departmentMapper = departmentMapper;
        this.departmentManagerMapper = departmentManagerMapper;
        this.boundleService = boundleService;
    }
    public AppResponse<String> changeDetail(DepartmentManagerUpdateDTO dto, AppLanguage lang) {
        String currentDeptId = SpringSecurityUtil.getCurrentUserDepartmentId();
        DepartmentEntity entity = findById(currentDeptId);

        if (entity == null) throw new ResourceNotFoundException(boundleService.getMessage("department.not.found", lang));
        if (!entity.getId().equals(currentDeptId)) {
            throw new AuthorizationDeniedException(boundleService.getMessage(
                    "department.update.permission.denied", lang
            ));
        }
        DepartmentEntity updatedEntity = departmentManagerMapper.toUpdatedEntity(dto);
        updatedEntity.setId(currentDeptId); // to ensure department ID is correct
        boolean success = update(updatedEntity, lang);
        String messageKey = success ? "department.update.completed.successfully" : "department.update.failed";
        return new AppResponse<>(boundleService.getMessage(messageKey, lang));
    }

    public DepartmentResponseDTO getMyDepartment(AppLanguage lang) {
        DepartmentEntity entity = findById(SpringSecurityUtil.getCurrentUserDepartmentId());
        if (entity == null) throw new ResourceNotFoundException(boundleService.getMessage("department.not.found", lang));
        return departmentMapper.toResponseDTO(entity);
    }
}
