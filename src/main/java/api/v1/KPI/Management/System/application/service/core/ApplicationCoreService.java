package api.v1.KPI.Management.System.application.service.core;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.app.util.AppResponseUtil;
import api.v1.KPI.Management.System.application.dto.core.ApplicationCreateDTO;
import api.v1.KPI.Management.System.application.dto.core.ApplicationResponseDTO;
import api.v1.KPI.Management.System.application.entity.ApplicationEntity;
import api.v1.KPI.Management.System.application.mapper.ApplicationMapper;
import api.v1.KPI.Management.System.application.service.ApplicationService;
import api.v1.KPI.Management.System.exception.exps.ResourceNotFoundException;
import api.v1.KPI.Management.System.offering.entity.OfferingEntity;
import api.v1.KPI.Management.System.offering.service.OfferingService;
import api.v1.KPI.Management.System.security.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationCoreService extends ApplicationService {

    @Autowired
    private ApplicationMapper applicationMapper;
    @Autowired
    private OfferingService offeringService;

    public ApplicationResponseDTO create(ApplicationCreateDTO dto, AppLanguage lang) {
        ApplicationEntity entity = applicationMapper.toCreatedEntity(dto);
        OfferingEntity offering = offeringService.findById(dto.getOfferingId());
        if (offering == null) {
            throw new ResourceNotFoundException("Offering not found");
        }
        entity.setSendProfileId(SpringSecurityUtil.getCurrentUserId());
        entity.setKpiBallLimit(offering.getKpiBall());
        return applicationMapper.toResponseDTO(save(entity));
    }

    public ApplicationResponseDTO getById(String id, AppLanguage lang) {
        return applicationMapper.toResponseDTO(findById(id));
    }


}


