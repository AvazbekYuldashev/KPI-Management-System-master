package api.v1.KPI.Management.System.department.service.user;

import api.v1.KPI.Management.System.department.dto.DepartmentResponseDTO;
import api.v1.KPI.Management.System.department.mapper.DepartmentMapper;
import api.v1.KPI.Management.System.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;


@Service
public class DepartmentUserService extends DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    public DepartmentResponseDTO userGetById(String id) {
        return departmentMapper.toResponseDTO(findById(id));
    }

    public PageImpl<DepartmentResponseDTO> userGetAll(int page, int size) {
        return getAll(page, size);
    }


}
