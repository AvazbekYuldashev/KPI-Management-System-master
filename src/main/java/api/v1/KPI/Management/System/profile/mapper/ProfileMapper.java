package api.v1.KPI.Management.System.profile.mapper;

import api.v1.KPI.Management.System.attach.service.AttachService;
import api.v1.KPI.Management.System.profile.dto.owner.ProfileOwnerChangeRoleDTO;
import api.v1.KPI.Management.System.profile.dto.owner.ProfileUpdateRoleDTO;
import api.v1.KPI.Management.System.profile.dto.profile.ProfileDTO;
import api.v1.KPI.Management.System.profile.dto.profile.ProfileResponseDTO;
import api.v1.KPI.Management.System.profile.entity.ProfileEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {
    @Autowired
    private AttachService attachService;


    public ProfileEntity toEntity(ProfileDTO dto) {
        return null;
    }

    public ProfileResponseDTO toInfoDTO(ProfileEntity entity) {
        ProfileResponseDTO dto = new ProfileResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setUsername(entity.getUsername());
        dto.setPhoto(entity.getPhotoId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setStatus(entity.getStatus());
        dto.setRole(entity.getRole());
        return dto;
    }


    public ProfileResponseDTO toallResponseDTO(ProfileEntity entity) {
        ProfileResponseDTO dto = new ProfileResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setUsername(entity.getUsername());
        dto.setPhoto(entity.getPhotoId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setStatus(entity.getStatus());
        dto.setRole(entity.getRole());
        return dto;

    }

}
