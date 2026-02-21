package api.v1.KPI.Management.System.department.mapper;

import api.v1.KPI.Management.System.department.dto.owner.DepartmentOwnerCreateDTO;
import api.v1.KPI.Management.System.department.dto.owner.DepartmentOwnerUpdateDTO;
import api.v1.KPI.Management.System.department.entity.DepartmentEntity;
import org.springframework.stereotype.Component;

@Component
public class DepartmentOwnerMapper {
    public DepartmentEntity toCreatedEntity(DepartmentOwnerCreateDTO dto){
        DepartmentEntity entity = new DepartmentEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setChiefId(dto.getChiefId());
        return entity;
    }
    public DepartmentEntity toUpdatedEntity(DepartmentOwnerUpdateDTO dto) {
        DepartmentEntity entity = new DepartmentEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setChiefId(dto.getChiefId());
        return entity;
    }
}
