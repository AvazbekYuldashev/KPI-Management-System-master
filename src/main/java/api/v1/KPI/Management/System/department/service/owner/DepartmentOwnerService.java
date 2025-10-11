package api.v1.KPI.Management.System.department.service.owner;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.department.dto.core.DepartmentResponseDTO;
import api.v1.KPI.Management.System.department.dto.owner.DepartmentOwnerCreateDTO;
import api.v1.KPI.Management.System.department.dto.owner.DepartmentOwnerUpdateDTO;
import api.v1.KPI.Management.System.department.mapper.DepartmentMapper;
import api.v1.KPI.Management.System.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentOwnerService extends DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;


    public DepartmentResponseDTO createOwner(DepartmentOwnerCreateDTO dto, String lang) {
        return departmentMapper.toResponseDTO(create(departmentMapper.toCreatedEntity(dto)));
    }

    public AppResponse<String> update(DepartmentOwnerUpdateDTO dto, String lang) {
        return changeDetail(departmentMapper.toUpdatedEntity(dto));
    }

    public AppResponse<String> delete(String id, String lang) {
        return deleteById(id);
    }

}
