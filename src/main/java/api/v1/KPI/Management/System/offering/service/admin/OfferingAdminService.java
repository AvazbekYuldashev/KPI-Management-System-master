package api.v1.KPI.Management.System.offering.service.admin;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.offering.dto.admin.OfferingAdminUpdateDTO;
import api.v1.KPI.Management.System.offering.entity.OfferingEntity;
import api.v1.KPI.Management.System.offering.mapper.OfferingMapper;
import api.v1.KPI.Management.System.offering.service.OfferingService;
import api.v1.KPI.Management.System.security.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;

@Service
public class OfferingAdminService extends OfferingService {
    @Autowired
    private OfferingMapper offeringMapper;

    public AppResponse<String> update(OfferingAdminUpdateDTO dto, String lang) {
        OfferingEntity entity = findById(dto.getId());
        if (!entity.getDepartmentId().equals(SpringSecurityUtil.getCurrentUserDepartmentId())){
            throw new AuthorizationDeniedException("Access denied");
        }
        return changeDetail(offeringMapper.toUpdatedEntity(dto));
    }

}
