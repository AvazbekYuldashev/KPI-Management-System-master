package api.v1.KPI.Management.System.application.mapper;

import api.v1.KPI.Management.System.application.dto.core.ApplicationCreateDTO;
import api.v1.KPI.Management.System.application.dto.core.ApplicationResponseDTO;
import api.v1.KPI.Management.System.application.entity.ApplicationEntity;
import org.springframework.stereotype.Component;

@Component
public class ApplicationMapper {
    public ApplicationEntity toCreatedEntity(ApplicationCreateDTO dto){
        ApplicationEntity entity = new ApplicationEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setDepartmentId(dto.getDepartmentId());
        entity.setBuildingId(dto.getBuildingId());
        entity.setOfferingId(dto.getOfferingId());
        return entity;
    }

    public ApplicationResponseDTO toResponseDTO(ApplicationEntity entity){
        ApplicationResponseDTO dto = new ApplicationResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.getStatus());
        dto.setKpiBall(entity.getKpiBall());
        dto.setKpiBallLimit(entity.getKpiBallLimit());
        dto.setDepartmentId(entity.getDepartmentId());
        dto.setVisible(entity.getVisible());

        dto.setSendProfileId(entity.getSendProfileId());
        dto.setAcceptorProfileId(entity.getAcceptorProfileId());
        dto.setBuildingId(entity.getBuildingId());
        dto.setOfferingId(entity.getOfferingId());

        dto.setCreatedDate(entity.getCreatedDate());
        dto.setAdminCheckedDate(entity.getAdminCheckedDate());
        dto.setEmployeeApprovedDate(entity.getEmployeeApprovedDate());
        dto.setEmployeeEndDate(entity.getEmployeeEndDate());
        dto.setLimitDate(entity.getLimitDate());
        dto.setUpdatedDate(entity.getUpdatedDate());
        return dto;
    }
}
