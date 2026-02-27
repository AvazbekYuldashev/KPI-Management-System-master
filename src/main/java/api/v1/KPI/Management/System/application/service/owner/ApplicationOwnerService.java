package api.v1.KPI.Management.System.application.service.owner;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.application.dto.core.ApplicationResponseDTO;
import api.v1.KPI.Management.System.application.dto.core.ApplicationStatusDTO;
import api.v1.KPI.Management.System.application.dto.manager.ApplicationFilterDTO;
import api.v1.KPI.Management.System.application.entity.ApplicationEntity;
import api.v1.KPI.Management.System.application.service.ApplicationService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ApplicationOwnerService extends ApplicationService {

    public Page<ApplicationResponseDTO> searchApplications(ApplicationFilterDTO dto, int page, Integer size, AppLanguage lang) {
        return ownerFilter(dto, page, size, lang);
    }

    public AppResponse<String> updateStatus(ApplicationStatusDTO dto, AppLanguage lang) {
        ApplicationEntity entity = findById(dto.getId());
        return changeStatus(dto, lang);
    }
}
