package api.v1.KPI.Management.System.offering.mapper;

import api.v1.KPI.Management.System.offering.dto.core.OfferingResponseDTO;
import api.v1.KPI.Management.System.offering.dto.manager.OfferingManagerCreateDTO;
import api.v1.KPI.Management.System.offering.dto.manager.OfferingManagerUpdateDTO;
import api.v1.KPI.Management.System.offering.dto.owner.OfferingOwnerCreateDTO;
import api.v1.KPI.Management.System.offering.dto.owner.OfferingOwnerUpdateDTO;
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
        dto.setDeadline(entity.getDeadline());
        dto.setCategoryId(entity.getCategoryId());
        dto.setDepartmentId(entity.getDepartmentId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedDate(entity.getUpdatedDate());
        dto.setBuildingId(entity.getBuildingId());
        dto.setVisible(entity.getVisible());
        return dto;
    }

}
