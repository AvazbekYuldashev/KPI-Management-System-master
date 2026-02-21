package api.v1.KPI.Management.System.offering.mapper;

import api.v1.KPI.Management.System.offering.dto.core.OfferingResponseDTO;
import api.v1.KPI.Management.System.offering.dto.manager.OfferingManagerCreateDTO;
import api.v1.KPI.Management.System.offering.dto.manager.OfferingManagerUpdateDTO;
import api.v1.KPI.Management.System.offering.dto.owner.OfferingCreateDTO;
import api.v1.KPI.Management.System.offering.dto.owner.OfferingUpdateDTO;
import api.v1.KPI.Management.System.offering.entity.OfferingEntity;
import org.springframework.stereotype.Component;

@Component
public class OfferingMapper {
    public OfferingEntity toCreatedEntity(OfferingCreateDTO dto) {
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

    public OfferingEntity toManagerCreatedEntity(OfferingManagerCreateDTO dto) {
        OfferingEntity entity = new OfferingEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setKpiBall(dto.getKpiBall());
        entity.setDeadline(dto.getDeadline());
        entity.setCategoryId(dto.getCategoryId());
        entity.setBuildingId(dto.getBuildingId());
        return entity;
    }

    public OfferingResponseDTO toResponseDTO(OfferingEntity entity) {
        OfferingResponseDTO dto = new OfferingResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setKpiBall(entity.getKpiBall());
        dto.setDeadline(entity.getDeadline());
        dto.setCategoryId(entity.getCategoryId());
        dto.setDepartmentId(entity.getDepartmentId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedDate(entity.getUpdatedDate());
        dto.setBuildingId(entity.getBuildingId());
        dto.setVisible(entity.getVisible());
        return dto;
    }

    public OfferingEntity toUpdatedEntity(OfferingUpdateDTO dto) {
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

    public OfferingEntity toManagerUpdatedEntity(OfferingManagerUpdateDTO dto) {
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
