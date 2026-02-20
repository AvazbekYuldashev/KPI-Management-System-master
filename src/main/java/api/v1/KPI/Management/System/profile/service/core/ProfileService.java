package api.v1.KPI.Management.System.profile.service.core;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.app.service.ResourceBoundleService;
import api.v1.KPI.Management.System.attach.service.AttachService;
import api.v1.KPI.Management.System.exception.exps.ResourceNotFoundException;
import api.v1.KPI.Management.System.profile.dto.profile.*;
import api.v1.KPI.Management.System.profile.entity.ProfileEntity;
import api.v1.KPI.Management.System.profile.enums.ProfileRole;
import api.v1.KPI.Management.System.profile.mapper.ProfileMapper;
import api.v1.KPI.Management.System.profile.repository.ProfileRepository;
import api.v1.KPI.Management.System.security.enums.GeneralStatus;
import api.v1.KPI.Management.System.security.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ResourceBoundleService boundleService;
    @Autowired
    private AttachService attachService;
    @Autowired
    private ProfileMapper profileMapper;

    public ProfileDTO getMe(AppLanguage lang) {
        String id = SpringSecurityUtil.getCurrentUserId();
        ProfileEntity profile = findById(id, lang);
        return profileMapper.toInfoDTO(profile);
    }

    public ProfileDTO getById(String id, AppLanguage lang) {
        return profileMapper.toInfoDTO(findById(id, lang));
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

    public AppResponse<String> changePhoto(String id, String photoId, AppLanguage lang) {
        ProfileEntity profile = findById(SpringSecurityUtil.getCurrentUserId(), lang);
        if (profile.getPhotoId() != null && !profile.getPhotoId().equals(photoId)){
            attachService.deleteSoft(profile.getPhotoId());
        }
        profileRepository.updatePhoto(id, photoId);
        return new AppResponse<>(boundleService.getMessage("update.successfully.completed",lang));
    }

    public AppResponse<String> deleteById(String id, AppLanguage lang) {
        ProfileEntity profile = findById(id, lang);
        profileRepository.deleteSoftById(profile.getId(), false);
        return new AppResponse<>(boundleService.getMessage("update.successfully.completed",lang));
    }

    public ProfileEntity findByUsername(String username, AppLanguage lang) {
        Optional<ProfileEntity> optional = profileRepository.findByUsernameAndVisibleTrue(username);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException(boundleService.getMessage("username.not.found", lang));
        }
        return optional.get();
    }
    public AppResponse<String> changeDepartment(String id, String departmentID, AppLanguage lang) {
        profileRepository.changeDepartment(id, departmentID);
        return new AppResponse<>(boundleService.getMessage("update.successfully.completed",lang));
    }

    public PageImpl<ProfileResponseDTO> getAll(int page, int size) {
        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ProfileEntity> pageObj = profileRepository.findAllPage(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        List<ProfileResponseDTO> response = pageObj.getContent().stream().map(profileMapper::toallResponseDTO).collect(Collectors.toList());
        long total = pageObj.getTotalElements();
        return new PageImpl<>(response, pageable, total);
    }

    public boolean updateRole(String id, ProfileRole role){
        return profileRepository.changeRole(id, role) > 0;
    }

    public boolean updateStatus(String id, GeneralStatus status){
        return profileRepository.changeStatus(id, status) > 0;
    }

    public ProfileEntity save(ProfileEntity entity) {
        return profileRepository.save(entity);
    }

    public int employeeUpdate(String id, String departmentId, Boolean b) {
        return profileRepository.changeDepartment(id, departmentId);
    }

}
