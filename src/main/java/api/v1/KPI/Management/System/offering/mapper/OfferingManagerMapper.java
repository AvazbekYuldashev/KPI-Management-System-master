package api.v1.KPI.Management.System.offering.mapper;

import api.v1.KPI.Management.System.offering.dto.manager.OfferingManagerCreateDTO;
import api.v1.KPI.Management.System.offering.dto.manager.OfferingManagerUpdateDTO;
import api.v1.KPI.Management.System.offering.entity.OfferingEntity;
import org.springframework.stereotype.Component;

@Component
public class OfferingManagerMapper {

    public OfferingEntity toCreatedEntity(OfferingManagerCreateDTO dto) {
        OfferingEntity entity = new OfferingEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setKpiBall(dto.getKpiBall());
        entity.setDeadline(dto.getDeadline());
        entity.setCategoryId(dto.getCategoryId());
        entity.setBuildingId(dto.getBuildingId());
        return entity;
    }

    public OfferingEntity toUpdatedEntity(OfferingManagerUpdateDTO dto) {
        OfferingEntity entity = new OfferingEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setKpiBall(dto.getKpiBall());
        entity.setDeadline(dto.getDeadline());
        entity.setCategoryId(dto.getCategoryId());
        entity.setBuildingId(dto.getBuildingId());
        return entity;
    }
}
