package api.v1.KPI.Management.System.application.mapper;

import api.v1.KPI.Management.System.application.dto.ApplicationResponseDTO;
import api.v1.KPI.Management.System.application.dto.admin.ApplicationAdminCreateDTO;
import api.v1.KPI.Management.System.application.entity.ApplicationEntity;
import org.springframework.stereotype.Component;

@Component
public class ApplicationMapper {
    public ApplicationResponseDTO toResponseDTO(ApplicationEntity entity) {
        ApplicationResponseDTO dto = new ApplicationResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setSendProfileId(entity.getSendProfileId());
        dto.setAcceptorProfileId(entity.getAcceptorProfileId());
        dto.setStatus(dto.getStatus());
        dto.setBuildingId(entity.getBuildingId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedDate(entity.getUpdatedDate());
        dto.setVisible(entity.getVisible());
        return dto;
    }

    public ApplicationEntity toAdminCreateEntity(ApplicationAdminCreateDTO dto) {
        ApplicationEntity entity = new ApplicationEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setSendProfileId(dto.getSendProfileId());
        entity.setAcceptorProfileId(dto.getAcceptorProfileId());
        entity.setBuildingId(dto.getBuildingId());
        return entity;
    }
}
