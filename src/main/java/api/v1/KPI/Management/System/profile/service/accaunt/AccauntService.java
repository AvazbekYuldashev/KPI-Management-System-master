package api.v1.KPI.Management.System.profile.service.accaunt;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.app.service.ResourceBoundleService;
import api.v1.KPI.Management.System.attach.service.AttachService;
import api.v1.KPI.Management.System.email.service.EmailHistoryService;
import api.v1.KPI.Management.System.email.service.EmailSendingService;
import api.v1.KPI.Management.System.email.util.EmailUtil;
import api.v1.KPI.Management.System.exception.exps.AppBadException;
import api.v1.KPI.Management.System.exception.exps.ResourceConflictException;
import api.v1.KPI.Management.System.jwt.util.JwtUtil;
import api.v1.KPI.Management.System.profile.dto.profile.ProfileDetailUpdateDTO;
import api.v1.KPI.Management.System.profile.dto.profile.ProfilePasswordUpdate;
import api.v1.KPI.Management.System.profile.dto.profile.ProfileUsernameUpdateDTO;
import api.v1.KPI.Management.System.profile.entity.ProfileEntity;
import api.v1.KPI.Management.System.profile.repository.ProfileRepository;
import api.v1.KPI.Management.System.profile.service.core.ProfileService;
import api.v1.KPI.Management.System.security.dto.CodeConfirmDTO;
import api.v1.KPI.Management.System.security.util.SpringSecurityUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccauntService extends ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ResourceBoundleService boundleService;
    @Autowired
    private BCryptPasswordEncoder bc;
    @Autowired
    private AttachService attachService;
    @Autowired
    private EmailSendingService emailSendingService;
    @Autowired
    private EmailHistoryService emailHistoryService;

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

    public AppResponse<String> updatePhoto(String photoId, AppLanguage lang) {
        return changePhoto(SpringSecurityUtil.getCurrentUserId(), photoId, lang);
    }
}
