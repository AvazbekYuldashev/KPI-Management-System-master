package api.v1.KPI.Management.System.application.service.manager;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.application.dto.core.ApplicationResponseDTO;
import api.v1.KPI.Management.System.application.entity.ApplicationEntity;
import api.v1.KPI.Management.System.application.enums.ApplicationStatus;
import api.v1.KPI.Management.System.application.mapper.ApplicationMapper;
import api.v1.KPI.Management.System.application.service.ApplicationService;
import api.v1.KPI.Management.System.exception.exps.AppBadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ApplicationManagerService extends ApplicationService {
    @Autowired
    private ApplicationMapper applicationMapper;

    public Page<ApplicationResponseDTO> getAll(int page, Integer size, AppLanguage lang) {
        return findAll(page,size);
    }

    public AppResponse<String> adminChecked(String id, AppLanguage lang) {
        ApplicationEntity entity = findById(id);
        if (!entity.getStatus().equals(ApplicationStatus.SENT)){
            throw new AppBadException("Application with id " + id + " is not sent");
        }
        return adminChecked(id, ApplicationStatus.APPROVED);
    }
}
