package api.v1.KPI.Management.System.application.service.admin;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.application.dto.core.ApplicationResponseDTO;
import api.v1.KPI.Management.System.application.dto.core.ApplicationStatusDTO;
import api.v1.KPI.Management.System.application.dto.manager.ApplicationFilterDTO;
import api.v1.KPI.Management.System.application.entity.ApplicationEntity;
import api.v1.KPI.Management.System.application.enums.ApplicationStatus;
import api.v1.KPI.Management.System.application.mapper.ApplicationMapper;
import api.v1.KPI.Management.System.application.service.ApplicationService;
import api.v1.KPI.Management.System.building.service.helper.BuildingHelperService;
import api.v1.KPI.Management.System.exception.exps.AppBadException;
import api.v1.KPI.Management.System.security.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationAdminService extends ApplicationService {

    @Autowired
    private BuildingHelperService buildingHelperService;
    @Autowired
    private  ApplicationMapper applicationMapper;


    public AppResponse<String> updateStatus(ApplicationStatusDTO dto, AppLanguage lang) {
        if (!dto.getStatus().isAdminActionAllowed()) {
            throw new AppBadException("Invalid status transition");
        }

        ApplicationEntity entity = findById(dto.getId());
        if (!entity.getStatus().equals(ApplicationStatus.SENT)){
            throw new AppBadException(
                    //messageSource.getMessage("status.invalid", null, lang)
                    "Status notogri"
            );
        }
        if (!entity.getDepartmentId().equals(SpringSecurityUtil.getCurrentUserDepartmentId())
                || !entity.getBuildingId().equals(SpringSecurityUtil.getCurrentUserBuildingId())) {

            throw new AuthorizationDeniedException(
                    "You are not authorized to perform this operation"
            );
        }
        return changeAdminStatus(dto, lang);
    }

    public Page<ApplicationResponseDTO> searchApplications(ApplicationFilterDTO dto, int page, Integer size, AppLanguage lang) {
        dto.setDepartmentId(SpringSecurityUtil.getCurrentUserDepartmentId());
        dto.setBuildingId(SpringSecurityUtil.getCurrentUserBuildingId());
        return ownerFilter(dto, page, size, lang);
    }


    public Page<ApplicationResponseDTO> getAllByBuildingId(String id, int page, Integer size, AppLanguage lang) {
        buildingHelperService.findById(id);
        Pageable pageable = PageRequest.of(page, size);
        return findAllByBuildingId(id, pageable, lang).map(applicationMapper::toResponseDTO);
    }
}
