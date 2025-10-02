package api.v1.KPI.Management.System.profile.service.owner;


import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.dto.FilterResultDTO;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.app.service.ResourceBoundleService;
import api.v1.KPI.Management.System.attach.service.AttachService;
import api.v1.KPI.Management.System.profile.dto.profile.ProfileResponseDTO;
import api.v1.KPI.Management.System.profile.dto.owner.*;
import api.v1.KPI.Management.System.profile.dto.profile.ProfileDetailUpdateDTO;
import api.v1.KPI.Management.System.profile.entity.ProfileEntity;
import api.v1.KPI.Management.System.profile.enums.ProfileRole;
import api.v1.KPI.Management.System.profile.mapper.ProfileMapper;
import api.v1.KPI.Management.System.profile.repository.CustomProfileRepository;
import api.v1.KPI.Management.System.profile.repository.ProfileRepository;
import api.v1.KPI.Management.System.profile.service.core.ProfileCoreService;
import api.v1.KPI.Management.System.profile.service.profile.ProfileService;
import api.v1.KPI.Management.System.security.enums.GeneralStatus;
import api.v1.KPI.Management.System.security.util.SpringSecurityUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import api.v1.KPI.Management.System.exception.exps.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileOwnerService extends ProfileCoreService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ResourceBoundleService boundleService;
    @Autowired
    private BCryptPasswordEncoder bc;
    @Autowired
    private CustomProfileRepository customProfileRepository;
    @Autowired
    private ProfileMapper profileMapper;
    @Autowired
    private AttachService attachService;


    /// Updates the current user's first and last name.
    /// If successful, returns a message stating that the update was successful.
    public AppResponse<String> updateDetail(ProfileDetailUpdateDTO dto, AppLanguage lang) {
        String id = SpringSecurityUtil.getCurrentUserId();
        profileRepository.updateDetail(id, dto.getName(), dto.getSurname());
        return new AppResponse<>(boundleService.getMessage("update.successfully.completed", lang));
    }

    /// The user checks the old password and updates the new password.
    /// If the old password is incorrect, an error is returned
    public AppResponse<String> updatePassword(ProfileOwnerUpdatePassword dto, AppLanguage lang) {
        ProfileEntity profile = findById(dto.getId(), lang);
        profileRepository.updatePassword(profile.getId(), bc.encode(dto.getPassword()));
        return new AppResponse<>(boundleService.getMessage("send.change.password.confirm.code", lang)); //todo message     // chhange confirm possword todo
    }


    /// Updates the status for the given user ID.
    public AppResponse<String> changeStatus(ProfileOwnerChangeStatusDTO dto, AppLanguage lang) {
        return updateStatus(dto.getProfileId(), dto.getStatus(), lang);
    }


    public Page<ProfileResponseDTO> filter(ProfileOwnerFilterDTO dto, int page, int size) {
        FilterResultDTO<ProfileEntity> resultDTO = customProfileRepository.filter(dto, page, size);
        List<ProfileResponseDTO> dtoList = resultDTO.getList().stream()
                .map(profileMapper::toallResponseDTO).toList();
        return new PageImpl<>(dtoList, PageRequest.of(page, size), resultDTO.getCount());
    }

    ///  Update the role for the given user ID.
    public AppResponse<String> changeRole(@Valid ProfileOwnerChangeRoleDTO dto, AppLanguage lang) {
        return updateRole(dto.getId(), dto.getRole(), lang);
    }


    public AppResponse<String> changePassword(@Valid ProfileOwnerUpdatePassword dto, AppLanguage lang) {
        profileRepository.changePassword(dto.getId(), dto.getPassword());
        return new AppResponse<>(boundleService.getMessage("update.successfully.completed", lang));
    }


    public AppResponse<String> photo(ProfileOwnerUpdatePhotoDTO dto, AppLanguage lang) {
        return updatePhoto(dto.getProfileId(), dto.getPhotoId(), lang);
    }
}
