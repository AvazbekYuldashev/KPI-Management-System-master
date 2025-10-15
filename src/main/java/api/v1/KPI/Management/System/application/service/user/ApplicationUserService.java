package api.v1.KPI.Management.System.application.service.user;

import api.v1.KPI.Management.System.application.dto.core.ApplicationCreateDTO;
import api.v1.KPI.Management.System.application.dto.core.ApplicationResponseDTO;
import api.v1.KPI.Management.System.application.entity.ApplicationEntity;
import api.v1.KPI.Management.System.application.mapper.ApplicationMapper;
import api.v1.KPI.Management.System.application.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService extends ApplicationService {
    @Autowired
    private ApplicationMapper applicationMapper;
    public ApplicationResponseDTO createUser(ApplicationCreateDTO dto, String lang) {
        ApplicationEntity entity = applicationMapper.toCreatedEnity(dto);
        return applicationMapper.toResponseDTO(create(entity));
    }
}
