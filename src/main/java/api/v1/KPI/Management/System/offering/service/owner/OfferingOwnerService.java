package api.v1.KPI.Management.System.offering.service.owner;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.app.util.AppResponseUtil;

import api.v1.KPI.Management.System.offering.dto.core.OfferingResponseDTO;
import api.v1.KPI.Management.System.offering.dto.owner.OfferingCreateDTO;
import api.v1.KPI.Management.System.offering.dto.owner.OfferingUpdateDTO;
import api.v1.KPI.Management.System.offering.entity.OfferingEntity;
import api.v1.KPI.Management.System.offering.mapper.OfferingMapper;
import api.v1.KPI.Management.System.offering.service.OfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferingOwnerService extends OfferingService {
    @Autowired
    private OfferingMapper offeringMapper;
    
    public OfferingResponseDTO ownerCreate(OfferingCreateDTO dto, AppLanguage lang){
        OfferingEntity entity = offeringMapper.toCreatedEntity(dto);
        return offeringMapper.toResponseDTO(create(entity));
    }

    public AppResponse<String> ownerUpdate(OfferingUpdateDTO dto, AppLanguage lang){
        OfferingEntity entity = offeringMapper.toUpdatedEntity(dto);
        return AppResponseUtil.chek(update(entity, lang));
    }
}
