package api.v1.KPI.Management.System.department.service.owner;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.department.dto.DepartmentResponseDTO;
import api.v1.KPI.Management.System.department.dto.owner.DepartmentOwnerCreateDTO;
import api.v1.KPI.Management.System.department.dto.owner.DepartmentOwnerUpdateDTO;
import api.v1.KPI.Management.System.department.mapper.DepartmentMapper;
import api.v1.KPI.Management.System.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;


@Service
public class DepartmentOwnerService extends DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    public DepartmentResponseDTO ownerCreate(DepartmentOwnerCreateDTO dto) {
        return departmentMapper.toResponseDTO(create(departmentMapper.toOwnerCreateEntity(dto)));
    }

    public DepartmentResponseDTO ownerGetById(String id) {
        return departmentMapper.toResponseDTO(findById(id));
    }

    public PageImpl<DepartmentResponseDTO> ownerGetAll(int page, int size) {
        return getAll(page, size);
    }

    public AppResponse<String> ownerDelete(String id) {
        return deleteById(id);
    }

    public AppResponse<String> ownerUpdate(String id, DepartmentOwnerUpdateDTO dto) {
        return null; //updateDetail(id, dto);
    }
}
