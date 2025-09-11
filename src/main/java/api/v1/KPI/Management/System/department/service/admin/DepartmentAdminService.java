package api.v1.KPI.Management.System.department.service.admin;

import api.v1.KPI.Management.System.department.dto.DepartmentResponseDTO;
import api.v1.KPI.Management.System.department.dto.admin.DepartmentAdminCreateDTO;
import api.v1.KPI.Management.System.department.mapper.DepartmentMapper;
import api.v1.KPI.Management.System.department.repository.DepartmentRepository;
import api.v1.KPI.Management.System.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentAdminService extends DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private DepartmentRepository departmentRepository;

    public DepartmentResponseDTO create(DepartmentAdminCreateDTO dto) {

    }
}
