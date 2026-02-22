package api.v1.KPI.Management.System.offering.mapper;

import api.v1.KPI.Management.System.offering.dto.owner.OfferingOwnerCreateDTO;
import api.v1.KPI.Management.System.offering.dto.owner.OfferingOwnerUpdateDTO;
import api.v1.KPI.Management.System.offering.entity.OfferingEntity;
import org.springframework.stereotype.Component;

@Component
public class OfferingOwnerMapper {
    public OfferingEntity toCreatedEntity(OfferingOwnerCreateDTO dto) {
        OfferingEntity entity = new OfferingEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setKpiBall(dto.getKpiBall());
        entity.setDeadline(dto.getDeadline());
        entity.setCategoryId(dto.getCategoryId());
        entity.setDepartmentId(dto.getDepartmentId());
        entity.setBuildingId(dto.getBuildingId());
        return entity;
    }

    public OfferingEntity toUpdatedEntity(OfferingOwnerUpdateDTO dto) {
        OfferingEntity entity = new OfferingEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setKpiBall(dto.getKpiBall());
        entity.setDeadline(dto.getDeadline());
        entity.setCategoryId(dto.getCategoryId());
        entity.setDepartmentId(dto.getDepartmentId());
        entity.setBuildingId(dto.getBuildingId());
        return entity;
    }
}
