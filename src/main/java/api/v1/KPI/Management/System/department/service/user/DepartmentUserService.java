package api.v1.KPI.Management.System.department.service.user;

import api.v1.KPI.Management.System.department.dto.DepartmentResponseDTO;
import api.v1.KPI.Management.System.department.entity.DepartmentEntity;
import api.v1.KPI.Management.System.department.service.DepartmentService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentUserService extends DepartmentService {

    public DepartmentResponseDTO userGetById(String id) {
        return getById(id);
    }

    public PageImpl<DepartmentResponseDTO> userGetAll(int page, int size) {
        return getAll(page, size);
    }


}
