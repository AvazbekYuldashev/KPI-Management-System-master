package api.v1.KPI.Management.System.application.service.user;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.application.dto.ApplicationDTO;
import api.v1.KPI.Management.System.application.dto.ApplicationResponseDTO;
import api.v1.KPI.Management.System.application.dto.user.ApplicationUserCreateDTO;
import api.v1.KPI.Management.System.application.dto.user.ApplicationUserUpdateDTO;
import api.v1.KPI.Management.System.application.entity.ApplicationEntity;
import api.v1.KPI.Management.System.application.enums.ApplicationStatus;
import api.v1.KPI.Management.System.application.mapper.ApplicationMapper;
import api.v1.KPI.Management.System.application.service.ApplicationService;
import api.v1.KPI.Management.System.security.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService extends ApplicationService {
    @Autowired
    private ApplicationMapper applicationMapper;

    ///
    public ApplicationResponseDTO userCreate(ApplicationUserCreateDTO dto) {
        String sendProfileId = SpringSecurityUtil.getCurrentUserId();
        ApplicationEntity entity = applicationMapper.toUserCreatedEntity(dto);
        entity.setSendProfileId(sendProfileId);
        ApplicationEntity responseEntity = create(entity);
        return applicationMapper.toResponseDTO(responseEntity);
    }

    ///
    public ApplicationResponseDTO userGetById(String id) {
        ApplicationEntity entity = findById(id);
        if (!entity.getSendProfileId().equals(SpringSecurityUtil.getCurrentUserId())){
            throw new AuthorizationDeniedException("Access denied");
        }
        return applicationMapper.toResponseDTO(entity);
    }

    ///
    public PageImpl<ApplicationResponseDTO> userGetAll(int page, int size) {
        return getAllMe(page, size, SpringSecurityUtil.getCurrentUserId());
    }

    ///
    public AppResponse<String> userUpdate(String id, ApplicationUserUpdateDTO dto) {
        ApplicationEntity entity = findById(id);
        String sendProfileId = SpringSecurityUtil.getCurrentUserId();
        if (!entity.getStatus().equals(ApplicationStatus.SENT)){
            throw new AuthorizationDeniedException("Access denied");
        }
        if (!entity.getSendProfileId().equals(sendProfileId)){
            throw new AuthorizationDeniedException("Access denied");
        }
        ApplicationDTO dto1 = applicationMapper.userUpdateToDTO(dto);
        return updateDetail(id, dto1);
    }
}
