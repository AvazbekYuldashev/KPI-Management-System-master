package api.v1.KPI.Management.System.profile.service.owner;


import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.dto.FilterResultDTO;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.app.service.ResourceBoundleService;
import api.v1.KPI.Management.System.department.entity.DepartmentEntity;
import api.v1.KPI.Management.System.department.service.DepartmentService;
import api.v1.KPI.Management.System.department.service.manager.DepartmentManagerService;
import api.v1.KPI.Management.System.exception.exps.ProfileStatusException;
import api.v1.KPI.Management.System.profile.dto.profile.ProfilePhotoUpdate;
import api.v1.KPI.Management.System.profile.dto.profile.ProfileResponseDTO;
import api.v1.KPI.Management.System.profile.dto.owner.*;
import api.v1.KPI.Management.System.profile.entity.ProfileEntity;
import api.v1.KPI.Management.System.profile.enums.ProfileRole;
import api.v1.KPI.Management.System.profile.mapper.ProfileMapper;
import api.v1.KPI.Management.System.profile.repository.CustomProfileRepository;
import api.v1.KPI.Management.System.profile.repository.ProfileRepository;
import api.v1.KPI.Management.System.profile.service.accaunt.AccauntService;
import api.v1.KPI.Management.System.profile.service.core.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileOwnerService extends ProfileService {
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
    private DepartmentManagerService departmentManagerService;


    public ProfileResponseDTO add(ProfileOwnerCreateDTO dto, AppLanguage lang) {
        ProfileEntity entity = profileMapper.toCreateEntity(dto, lang);
        entity.setPassword(bc.encode(dto.getPassword()));
        entity.setLanguage(lang);
        return profileMapper.toallResponseDTO(save(entity));
    }

    /// Updates the status for the given user ID.
    public AppResponse<String> changeStatus(ProfileOwnerChangeStatusDTO dto, AppLanguage lang) {
        profileRepository.changeStatus(dto.getId(), dto.getStatus());
        return new AppResponse<>(boundleService.getMessage("update.successfully.completed", lang));
    }

    public Page<ProfileResponseDTO> filter(ProfileOwnerFilterDTO dto, int page, int size) {
        FilterResultDTO<ProfileEntity> resultDTO = customProfileRepository.filter(dto, page, size);
        List<ProfileResponseDTO> dtoList = resultDTO.getList().stream()
                .map(profileMapper::toallResponseDTO).toList();
        return new PageImpl<>(dtoList, PageRequest.of(page, size), resultDTO.getCount());
    }

    ///  Update the role for the given user ID.
    public AppResponse<String> changeRole(@Valid ProfileOwnerChangeRoleDTO dto, AppLanguage lang) {
        profileRepository.changeRole(dto.getId(), dto.getRole());
        return new AppResponse<>(boundleService.getMessage("update.successfully.completed", lang));
    }

    public AppResponse<String> changePassword(@Valid ProfileOwnerUpdatePassword dto, AppLanguage lang) {
        profileRepository.changePassword(dto.getId(), dto.getPassword());
        return new AppResponse<>(boundleService.getMessage("update.successfully.completed", lang));
    }

    public AppResponse<String> deleteByIdAS(String id, AppLanguage lang) {
        ProfileEntity profile = findById(id, lang);
        if (profile.getStatus().equals(ProfileRole.ROLE_OWNER)){
            throw new ProfileStatusException("asd");
        }
        return deleteById(id, lang);
    }

    public void updateEmployee(String id) {
        findById(id, AppLanguage.UZ);
        employeeUpdate(id, Boolean.TRUE);
    }

    public AppResponse<String> updateDepartment(ProfileOwnerChangeDepartmentDTO dto, AppLanguage lang) {
        ProfileEntity profile = findById(dto.getId(), lang);
        DepartmentEntity department = departmentManagerService.findById(dto.getDepartmentId());

        return changeDepartment(dto, lang);
    }

    public AppResponse<String> updatePhoto(ProfilePhotoUpdate dto, AppLanguage lang) {
        return changePhoto(dto.getId(), dto.getPhotoId(), lang);
    }



}
