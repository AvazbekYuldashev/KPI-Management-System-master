package api.v1.KPI.Management.System.department.service.owner;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.app.util.AppResponseUtil;
import api.v1.KPI.Management.System.department.dto.core.DepartmentCreatedDTO;
import api.v1.KPI.Management.System.department.dto.core.DepartmentUpdateDTO;
import api.v1.KPI.Management.System.department.dto.core.DepartmentResponseDTO;
import api.v1.KPI.Management.System.department.entity.DepartmentEntity;
import api.v1.KPI.Management.System.department.mapper.DepartmentMapper;
import api.v1.KPI.Management.System.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentOwnerService extends DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    public DepartmentResponseDTO ownerCreate(DepartmentCreatedDTO dto, AppLanguage lang){
        DepartmentEntity entity = departmentMapper.toCreatedEntity(dto);
        return departmentMapper.toResponseDTO(create(entity));
    }

    public AppResponse<String> ownerUpdate(DepartmentUpdateDTO dto, AppLanguage lang){
        DepartmentEntity entity = departmentMapper.toUpdatedEntity(dto);
        return AppResponseUtil.chek(update(entity, lang));
    }

}
