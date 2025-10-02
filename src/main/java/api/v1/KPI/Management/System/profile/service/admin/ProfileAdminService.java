package api.v1.KPI.Management.System.profile.service.admin;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.exception.exps.ProfileStatusException;
import api.v1.KPI.Management.System.profile.dto.admin.ProfileAdminChangeRoleDTO;
import api.v1.KPI.Management.System.profile.dto.admin.ProfileAdminChangeStatusDTO;
import api.v1.KPI.Management.System.profile.dto.owner.*;
import api.v1.KPI.Management.System.profile.dto.profile.ProfileResponseDTO;
import api.v1.KPI.Management.System.profile.entity.ProfileEntity;
import api.v1.KPI.Management.System.profile.enums.ProfileRole;
import api.v1.KPI.Management.System.profile.mapper.ProfileMapper;
import api.v1.KPI.Management.System.profile.service.core.ProfileCoreService;
import api.v1.KPI.Management.System.profile.service.profile.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;


@Service
public class ProfileAdminService extends ProfileCoreService {

    public AppResponse<String> changeStatus(@Valid ProfileAdminChangeStatusDTO dto, AppLanguage lang) {
        ProfileEntity profile = findById(dto.getProfileId(), lang);
        if (profile.getRole().equals(ProfileRole.ROLE_OWNER)){
            throw new AuthorizationDeniedException("You are not allowed to update this KPI.");
        }
        return updateStatus(profile.getId(), dto.getStatus(), lang);
    }

    public AppResponse<String> changeRole(@Valid ProfileAdminChangeRoleDTO dto, AppLanguage lang) {
        if (dto.getRole().equals(ProfileRole.ROLE_OWNER)){
            throw new AuthorizationDeniedException("You are not allowed to update this KPI.");
        }
        ProfileEntity profile = findById(dto.getProfileId(), lang);
        if (profile.getRole().equals(ProfileRole.ROLE_OWNER)){
            throw new AuthorizationDeniedException("You are not allowed to update this KPI.");
        }
        return updateRole(dto.getProfileId(), dto.getRole(), lang);

    }
}
