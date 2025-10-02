package api.v1.KPI.Management.System.profile.service.core;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.app.service.ResourceBoundleService;
import api.v1.KPI.Management.System.attach.service.AttachService;
import api.v1.KPI.Management.System.email.service.EmailHistoryService;
import api.v1.KPI.Management.System.email.service.EmailSendingService;
import api.v1.KPI.Management.System.email.util.EmailUtil;
import api.v1.KPI.Management.System.exception.exps.AppBadException;
import api.v1.KPI.Management.System.exception.exps.ResourceConflictException;
import api.v1.KPI.Management.System.exception.exps.ResourceNotFoundException;
import api.v1.KPI.Management.System.jwt.util.JwtUtil;
import api.v1.KPI.Management.System.profile.dto.profile.*;
import api.v1.KPI.Management.System.profile.entity.ProfileEntity;
import api.v1.KPI.Management.System.profile.enums.ProfileRole;
import api.v1.KPI.Management.System.profile.mapper.ProfileMapper;
import api.v1.KPI.Management.System.profile.repository.ProfileRepository;
import api.v1.KPI.Management.System.security.dto.CodeConfirmDTO;
import api.v1.KPI.Management.System.security.enums.GeneralStatus;
import api.v1.KPI.Management.System.security.util.SpringSecurityUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfileCoreService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ResourceBoundleService boundleService;
    @Autowired
    private BCryptPasswordEncoder bc;
    @Autowired
    private EmailSendingService emailSendingService;
    @Autowired
    private EmailHistoryService emailHistoryService;
    @Autowired
    private AttachService attachService;
    @Autowired
    private ProfileMapper profileMapper;

    public ProfileResponseDTO getMe(AppLanguage lang) {
        String id = SpringSecurityUtil.getCurrentUserId();
        ProfileEntity profile = findById(id, lang);
        return profileMapper.toInfoDTO(profile);
    }

    /// Updates the current user's first and last name.
    /// If successful, returns a message stating that the update was successful.
    public AppResponse<String> updateDetail(ProfileDetailUpdateDTO dto, AppLanguage lang) {
        String id = SpringSecurityUtil.getCurrentUserId();
        profileRepository.updateDetail(id, dto.getName(), dto.getSurname());
        return new AppResponse<>(boundleService.getMessage("update.successfully.completed", lang));
    }

    /// The user checks the old password and updates the new password.
    /// If the old password is incorrect, an error is returned
    public AppResponse<String> updatePassword(@Valid ProfilePasswordUpdate dto, AppLanguage lang) {
        String id = SpringSecurityUtil.getCurrentUserId();
        ProfileEntity profile = findById(id, lang);
        if (!bc.matches(dto.getOldPassword(), profile.getPassword())){
            throw new AppBadException(boundleService.getMessage("wrong.password", lang));
        }
        profileRepository.updatePassword(id, bc.encode(dto.getNewPassword()));
        return new AppResponse<>(boundleService.getMessage("send.change.password.confirm.code", lang));     // chhange confirm possword todo
    }

    /// The user's username is temporarily saved and a verification code is sent to change it.
    /// If the username already belongs to another user, an error is returned.
    public AppResponse<String> updateUsername(@Valid ProfileUsernameUpdateDTO dto, AppLanguage lang) {
        Optional<ProfileEntity> optional = profileRepository.findByUsernameAndVisibleTrue(dto.getUsername());
        if (optional.isPresent()){
            if (!optional.get().getUsername().equals(SpringSecurityUtil.getCurrentProfile().getUsername())){
                throw new ResourceConflictException(boundleService.getMessage("username.conflict", lang));
            }
        }
        if (EmailUtil.isEmail(dto.getUsername())){
            emailSendingService.sendUsernameChangeConfirmEmail(dto.getUsername(), lang);
        }
        profileRepository.updateTempUsername(SpringSecurityUtil.getCurrentUserId(), dto.getUsername());
        return new AppResponse<>(boundleService.getMessage("send.change.username.confirm.code", lang));
    }

    /// Confirms username change: checks the code sent for the temporary username, updates the primary username, and returns the updated JWT token.
    public AppResponse<String> updateUsernameConfirm(CodeConfirmDTO dto, AppLanguage lang) {
        ProfileEntity profile = findById(SpringSecurityUtil.getCurrentUserId(), lang);
        if (EmailUtil.isEmail(profile.getUsername())){
            emailHistoryService.check(profile.getTempUsername(), dto.getCode(), lang);
        }
        profileRepository.updateUsername(SpringSecurityUtil.getCurrentUserId(), profile.getTempUsername());
        return new AppResponse<>(JwtUtil.encode(profile.getTempUsername(), profile.getId(), profile.getRole()));
    }

    /// Finds the profile by the given ID.
    /// Returns an error if not found.
    public ProfileEntity findById(String id, AppLanguage lang) {
        Optional<ProfileEntity> optional = profileRepository.findByIdAndVisibleTrue(id);
        if (optional.isEmpty()){
            throw new ResourceNotFoundException(boundleService.getMessage("profile.not.found", lang) + ": " + id);
        }
        return optional.get();
    }


    public AppResponse<String> deletebyId(String id, AppLanguage lang) {
        if (!SpringSecurityUtil.getCurrentUserId().equals(id)
                && (!SpringSecurityUtil.haseRole().equals(ProfileRole.ROLE_ADMIN)
                && !SpringSecurityUtil.haseRole().equals(ProfileRole.ROLE_OWNER))) {
            throw new AuthorizationDeniedException("You are not allowed to update this KPI.");
        }
        ProfileEntity profile = findById(id, lang);
        profileRepository.deleteSoftById(profile.getId(), false);
        return new AppResponse<>(boundleService.getMessage("update.successfully.completed",lang));
    }

    /// Finds an active (visible) profile for the given username.
    /// Returns an error if not found.
    public ProfileEntity findByUsername(String username, AppLanguage lang) {
        Optional<ProfileEntity> optional = profileRepository.findByUsernameAndVisibleTrue(username);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException(boundleService.getMessage("username.not.found", lang));
        }
        return optional.get();
    }

    public PageImpl<ProfileResponseDTO> getAll(int page, int size) {
        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ProfileEntity> pageObj = profileRepository.findAllPage(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        List<ProfileResponseDTO> response = pageObj.getContent().stream().map(profileMapper::toallResponseDTO).collect(Collectors.toList());
        long total = pageObj.getTotalElements();
        return new PageImpl<>(response, pageable, total);
    }

    public AppResponse<String> updatePhoto(String profileId, String photoId, AppLanguage lang) {
        ProfileEntity profile = findById(profileId, lang);
        if (profile.getPhotoId() != null && !profile.getPhotoId().equals(photoId)){
            attachService.deleteSoft(profile.getPhotoId());
        }
        profileRepository.updatePhoto(profile.getId(), photoId);

        return new AppResponse<>(boundleService.getMessage("update.successfully.completed",lang));
    }



    ///  private Service method
    public AppResponse<String> updateRole(String id, ProfileRole role, AppLanguage lang) {
        int effectedRow = profileRepository.changeRole(id, role);
        if (effectedRow > 0){
            return new AppResponse<>(boundleService.getMessage("update.successfully.completed", lang));
        }
        else {
            throw new AppBadException(boundleService.getMessage("update.failed", lang));
        }
    }

    public ProfileResponseDTO getById(String id, AppLanguage lang) {
        return profileMapper.toallResponseDTO(findById(id, lang));
    }

    public List<ProfileResponseDTO> findByRole(AppLanguage lang) {
        return profileRepository.findAllByRole(ProfileRole.ROLE_MANAGER).
                stream().map(profileMapper::toallResponseDTO).toList();
    }

    public AppResponse<String> updateStatus(@NotBlank String id, @NotBlank GeneralStatus status, AppLanguage lang) {
        int effectedRow = profileRepository.changeStatus(id, status);
        if (effectedRow > 0){
            return new AppResponse<>(boundleService.getMessage("update.successfully.completed", lang));
        }
        else {
            throw new AppBadException(boundleService.getMessage("update.failed", lang));
        }
    }
}
