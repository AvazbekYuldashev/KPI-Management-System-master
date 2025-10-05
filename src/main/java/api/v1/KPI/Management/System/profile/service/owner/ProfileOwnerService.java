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
import api.v1.KPI.Management.System.profile.mapper.ProfileMapper;
import api.v1.KPI.Management.System.profile.repository.CustomProfileRepository;
import api.v1.KPI.Management.System.profile.repository.ProfileRepository;
import api.v1.KPI.Management.System.profile.service.profile.ProfileService;
import api.v1.KPI.Management.System.security.util.SpringSecurityUtil;
import jakarta.validation.Valid;
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

}
