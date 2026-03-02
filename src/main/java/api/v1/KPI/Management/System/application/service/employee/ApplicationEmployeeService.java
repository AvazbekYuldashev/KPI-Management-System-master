package api.v1.KPI.Management.System.application.service.employee;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.application.dto.core.ApplicationResponseDTO;
import api.v1.KPI.Management.System.application.dto.core.ApplicationStatusDTO;
import api.v1.KPI.Management.System.application.dto.manager.ApplicationFilterDTO;
import api.v1.KPI.Management.System.application.entity.ApplicationEntity;
import api.v1.KPI.Management.System.application.enums.ApplicationStatus;
import api.v1.KPI.Management.System.application.mapper.ApplicationMapper;
import api.v1.KPI.Management.System.application.service.ApplicationService;
import api.v1.KPI.Management.System.exception.exps.AppBadException;
import api.v1.KPI.Management.System.security.util.SpringSecurityUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplicationEmployeeService extends ApplicationService {

    private final ApplicationMapper applicationMapper;

    public ApplicationEmployeeService(ApplicationMapper applicationMapper) {
        super();
        this.applicationMapper = applicationMapper;
    }

    public Page<ApplicationResponseDTO> searchApplications(ApplicationFilterDTO dto, int page, Integer size, AppLanguage lang) {
        dto.setDepartmentId(SpringSecurityUtil.getCurrentUserDepartmentId());
        dto.setBuildingId(SpringSecurityUtil.getCurrentUserBuildingId());
        if (dto.getAcceptorProfileId() != null){
            dto.setAcceptorProfileId(SpringSecurityUtil.getCurrentUserId());
        }
        return ownerFilter(dto, page, size, lang);
    }

    public AppResponse<String> updateStatus(ApplicationStatusDTO dto, AppLanguage lang) {
        if (!dto.getStatus().equals(ApplicationStatus.IN_PROGRESS)
                && !dto.getStatus().equals(ApplicationStatus.REVIEW)
        && !dto.getStatus().equals(ApplicationStatus.DENIED)) {
            throw new AppBadException(
                    //messageSource.getMessage("status.invalid", null, lang)
                    "Status notogri"
            );
        }

        ApplicationEntity entity = findById(dto.getId());
        if (!entity.getDepartmentId().equals(SpringSecurityUtil.getCurrentUserDepartmentId())
                || !entity.getBuildingId().equals(SpringSecurityUtil.getCurrentUserBuildingId())) {

            throw new AuthorizationDeniedException(
                    "You are not authorized to perform this operation"
            );
        }
        return changeEmployeeStatus(dto, lang);
    }

    public Page<ApplicationResponseDTO> getAllByStatusAcceptPage(int page, Integer size, AppLanguage lang) {
        Pageable pageable = PageRequest.of(page, size);
        return findAllByStatusAcceptPage(pageable, SpringSecurityUtil.getCurrentUserBuildingId()).map(entity -> applicationMapper.toResponseDTO(entity));
    }

    public Page<ApplicationResponseDTO> getAllByMyIdPage(int page, Integer size, AppLanguage lang) {
        Pageable pageable = PageRequest.of(page, size);
        String id = SpringSecurityUtil.getCurrentUserId();
        return findAllByMyIdPage(pageable, id, SpringSecurityUtil.getCurrentUserBuildingId()).map(entity -> applicationMapper.toResponseDTO(entity));
    }


}
