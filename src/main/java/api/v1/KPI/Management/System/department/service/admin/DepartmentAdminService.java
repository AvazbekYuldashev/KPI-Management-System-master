package api.v1.KPI.Management.System.department.service.admin;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.department.dto.DepartmentResponseDTO;
import api.v1.KPI.Management.System.department.dto.admin.DepartmentAdminCreateDTO;
import api.v1.KPI.Management.System.department.dto.admin.DepartmentAdminUpdateDTO;
import api.v1.KPI.Management.System.department.mapper.DepartmentMapper;
import api.v1.KPI.Management.System.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;


@Service
public class DepartmentAdminService extends DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    public DepartmentResponseDTO adminCreate(DepartmentAdminCreateDTO dto) {
        return departmentMapper.toResponseDTO(create(departmentMapper.toAdminCreateEntity(dto)));
    }

    public DepartmentResponseDTO adminGetById(String id) {
        return departmentMapper.toResponseDTO(findById(id));
    }

    public PageImpl<DepartmentResponseDTO> adminGetAll(int page, int size) {
        return getAll(page, size);
    }

    public AppResponse<String> adminDelete(String id) {
        return deleteById(id);
    }

    public AppResponse<String> adminUpdate(String id, DepartmentAdminUpdateDTO dto) {
        return updateDetail(id, dto);
    }
}
