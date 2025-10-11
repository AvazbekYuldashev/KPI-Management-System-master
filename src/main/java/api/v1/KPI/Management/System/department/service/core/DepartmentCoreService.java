package api.v1.KPI.Management.System.department.service.core;

import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.department.dto.core.DepartmentResponseDTO;
import api.v1.KPI.Management.System.department.mapper.DepartmentMapper;
import api.v1.KPI.Management.System.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class DepartmentCoreService extends DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    public DepartmentResponseDTO getById(String id, AppLanguage lang) {
        return departmentMapper.toResponseDTO(findById(id));
    }

    public Page<DepartmentResponseDTO> getAll(int page, Integer size, AppLanguage lang) {
        return findAll(page, size);
    }
}
