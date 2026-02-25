package api.v1.KPI.Management.System.department.mapper;

import api.v1.KPI.Management.System.department.dto.manager.DepartmentManagerUpdateDTO;
import api.v1.KPI.Management.System.department.entity.DepartmentEntity;
import org.springframework.stereotype.Component;

@Component
public class DepartmentManagerMapper {
    public DepartmentEntity toUpdatedEntity(DepartmentManagerUpdateDTO dto) {
        DepartmentEntity entity = new DepartmentEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        return entity;
    }
}
