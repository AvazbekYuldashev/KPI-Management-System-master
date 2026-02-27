package api.v1.KPI.Management.System.department.service.core;

import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.department.dto.core.DepartmentResponseDTO;
import api.v1.KPI.Management.System.department.entity.DepartmentEntity;
import api.v1.KPI.Management.System.department.mapper.DepartmentMapper;
import api.v1.KPI.Management.System.department.service.DepartmentService;
import api.v1.KPI.Management.System.exception.exps.ResourceNotFoundException;
import api.v1.KPI.Management.System.security.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentCoreService extends DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    public DepartmentResponseDTO getById(String id, AppLanguage lang){
        DepartmentEntity entity = findById(id);
        if (entity != null) return departmentMapper.toResponseDTO(entity);
        throw new ResourceNotFoundException("Department Not Found");
    }

    public DepartmentResponseDTO getByTitle(String title, AppLanguage lang) {
        DepartmentEntity entity = findByTitle(title);
        if (entity != null) return departmentMapper.toResponseDTO(entity);
        throw new ResourceNotFoundException("Department Not Found");
    }

    public DepartmentResponseDTO getByMyId(AppLanguage lang) {
        DepartmentEntity entity = findById(SpringSecurityUtil.getCurrentUserDepartmentId());
        if (entity != null) return departmentMapper.toResponseDTO(entity);
        throw new ResourceNotFoundException("Your Department Not Found");
    }
}
