package api.v1.KPI.Management.System.offering.service.manager;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.app.service.ResourceBoundleService;
import api.v1.KPI.Management.System.app.util.AppResponseUtil;
import api.v1.KPI.Management.System.offering.dto.core.OfferingResponseDTO;
import api.v1.KPI.Management.System.offering.dto.manager.OfferingManagerCreateDTO;
import api.v1.KPI.Management.System.offering.dto.manager.OfferingManagerUpdateDTO;
import api.v1.KPI.Management.System.offering.entity.OfferingEntity;
import api.v1.KPI.Management.System.offering.mapper.OfferingManagerMapper;
import api.v1.KPI.Management.System.offering.mapper.OfferingMapper;
import api.v1.KPI.Management.System.offering.service.OfferingService;
import api.v1.KPI.Management.System.security.util.SpringSecurityUtil;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;

@Service
public class OfferingManagerService extends OfferingService {

    private final OfferingMapper offeringMapper;
    private final OfferingManagerMapper offeringManagerMapper;
    private final ResourceBoundleService boundleService;

    public OfferingManagerService(OfferingMapper offeringMapper,
                                  OfferingManagerMapper offeringManagerMapper,
                                  ResourceBoundleService boundleService) {
        this.offeringMapper = offeringMapper;
        this.offeringManagerMapper = offeringManagerMapper;
        this.boundleService = boundleService;
    }

    public OfferingResponseDTO managerCreate(OfferingManagerCreateDTO dto, AppLanguage lang) {
        OfferingEntity entity = offeringManagerMapper.toCreatedEntity(dto);
        entity.setDepartmentId(SpringSecurityUtil.getCurrentUserDepartmentId());
        return offeringMapper.toResponseDTO(create(entity));
    }

    public AppResponse<String> managerUpdate(OfferingManagerUpdateDTO dto, AppLanguage lang){
        OfferingEntity offering = findByIdAndVisibleTrue(dto.getId());
        if (!offering.getDepartmentId().equals(SpringSecurityUtil.getCurrentUserDepartmentId())){
            throw new AuthorizationDeniedException(boundleService.getMessage("offering.update.permission.denied", lang));
        }
        OfferingEntity entity = offeringManagerMapper.toUpdatedEntity(dto);
        return AppResponseUtil.chek(update(entity, lang));
    }


}
