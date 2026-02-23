package api.v1.KPI.Management.System.profile.mapper;

import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.attach.service.AttachService;
import api.v1.KPI.Management.System.profile.dto.owner.ProfileOwnerCreateDTO;
import api.v1.KPI.Management.System.profile.dto.profile.ProfileDTO;
import api.v1.KPI.Management.System.profile.dto.profile.ProfileResponseDTO;
import api.v1.KPI.Management.System.profile.entity.ProfileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {
    @Autowired
    private AttachService attachService;


    public ProfileDTO toInfoDTO(ProfileEntity entity) {
        ProfileDTO dto = new ProfileDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setUsername(entity.getUsername());
        dto.setPhoto(attachService.getDTOById(entity.getPhotoId()));
        dto.setDepartmentId(entity.getDepartmentId());
        dto.setBuildingId(entity.getBuildingId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setStatus(entity.getStatus());
        dto.setRole(entity.getRole());
        dto.setIsEmployee(entity.getIsEmployee());

        return dto;
    }


    public ProfileResponseDTO toallResponseDTO(ProfileEntity entity) {
        ProfileResponseDTO dto = new ProfileResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setUsername(entity.getUsername());
        dto.setPhotoId(entity.getPhotoId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setStatus(entity.getStatus());
        dto.setRole(entity.getRole());
        dto.setDepartmentId(entity.getDepartmentId());
        dto.setBuildingId(entity.getBuildingId());
        dto.setIsEmployee(entity.getIsEmployee());
        return dto;

    }

    public ProfileEntity toCreateEntity(ProfileOwnerCreateDTO dto, AppLanguage lang) {
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setUsername(dto.getUsername());
        entity.setPhotoId(dto.getPhotoId());
        entity.setStatus(dto.getStatus());
        entity.setRole(dto.getRole());
        entity.setDepartmentId(entity.getDepartmentId());
        entity.setBuildingId(entity.getBuildingId());
        return entity;
    }

}
