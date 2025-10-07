package api.v1.KPI.Management.System.profile.service.admin;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.app.util.AppResponseUtil;
import api.v1.KPI.Management.System.exception.exps.ProfileStatusException;
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
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;


@Service
public class ProfileAdminService extends ProfileCoreService {

    @Autowired
    private ProfileMapper profileMapper;


    public AppResponse<String> changeRole(@Valid ProfileUpdateRoleDTO dto, AppLanguage lang) {
        if (dto.getRole().equals(ProfileRole.ROLE_OWNER)){
            throw new ProfileStatusException("asd");
        }
        ProfileEntity entity = findById(dto.getId(), lang);
        if (entity.getStatus().equals(ProfileRole.ROLE_OWNER)){
            throw new ProfileStatusException("asd");
        }
        return AppResponseUtil.chek(updateRole(dto.getId(), dto.getRole()));
    }

    public AppResponse<String> changeStatus(@Valid ProfileOwnerChangeStatusDTO dto, AppLanguage lang) {
        ProfileEntity entity = findById(dto.getId(), lang);
        if (entity.getStatus().equals(ProfileRole.ROLE_OWNER)){
            throw new ProfileStatusException("asd");
        }
        return AppResponseUtil.chek(updateStatus(dto.getId(), dto.getStatus()));
    }

    public Page<ProfileResponseDTO> filter(ProfileOwnerFilterDTO dto, int currentPage, Integer size) {
        return null;
    }

    public AppResponse<String> deleteById(String id, AppLanguage lang) {
        ProfileEntity profile = findById(id, lang);
        if (profile.getStatus().equals(ProfileRole.ROLE_OWNER)){
            throw new ProfileStatusException("asd");
        }
        return deletebyId(id, lang);
    }
}
