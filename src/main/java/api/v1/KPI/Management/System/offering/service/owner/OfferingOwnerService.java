package api.v1.KPI.Management.System.offering.service.owner;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.app.util.AppResponseUtil;

import api.v1.KPI.Management.System.offering.dto.core.OfferingResponseDTO;
import api.v1.KPI.Management.System.offering.dto.owner.OfferingOwnerCreateDTO;
import api.v1.KPI.Management.System.offering.dto.owner.OfferingOwnerUpdateDTO;
import api.v1.KPI.Management.System.offering.entity.OfferingEntity;
import api.v1.KPI.Management.System.offering.mapper.OfferingMapper;
import api.v1.KPI.Management.System.offering.mapper.OfferingOwnerMapper;
import api.v1.KPI.Management.System.offering.service.OfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OfferingOwnerService extends OfferingService {
    @Autowired
    private OfferingMapper offeringMapper;
    @Autowired
    private OfferingOwnerMapper  offeringOwnerMapper;
    
    public OfferingResponseDTO ownerCreate(OfferingOwnerCreateDTO dto, AppLanguage lang){
        OfferingEntity entity = offeringOwnerMapper.toCreatedEntity(dto);
        return offeringMapper.toResponseDTO(create(entity));
    }

    public AppResponse<String> ownerUpdate(OfferingOwnerUpdateDTO dto, AppLanguage lang){
        OfferingEntity entity = offeringOwnerMapper.toUpdatedEntity(dto);
        return AppResponseUtil.chek(update(entity, lang));
    }

    public Page<OfferingResponseDTO> getAllPage(int page, Integer size, AppLanguage lang) {
        Pageable pageable = PageRequest.of(page, size);
        return findAllPage(pageable).map(entity -> offeringMapper.toResponseDTO(entity));
    }


}
