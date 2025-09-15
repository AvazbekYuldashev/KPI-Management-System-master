package api.v1.KPI.Management.System.offering.mapper;

import api.v1.KPI.Management.System.offering.dto.OfferingResponseDTO;
import api.v1.KPI.Management.System.offering.dto.admin.OfferingAdminCreateDTO;
import api.v1.KPI.Management.System.offering.entity.OfferingEntity;
import org.springframework.stereotype.Component;

@Component
public class OfferingMapper {

    public OfferingResponseDTO toResponseDTO(OfferingEntity entity) {
        OfferingResponseDTO dto = new OfferingResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setKpiBall(entity.getKpiBall());
        dto.setCategoryId(entity.getCategoryId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedDate(entity.getUpdatedDate());
        dto.setVisible(entity.getVisible());
        return dto;
    }


    public OfferingEntity toAdminCreateEntity(OfferingAdminCreateDTO dto) {
        OfferingEntity entity = new OfferingEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setKpiBall(dto.getKpiBall());
        entity.setCategoryId(dto.getCategoryId());
        return  entity;
    }
}
