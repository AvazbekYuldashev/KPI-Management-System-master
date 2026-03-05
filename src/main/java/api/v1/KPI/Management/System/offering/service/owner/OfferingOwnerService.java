package api.v1.KPI.Management.System.offering.service.owner;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.app.service.ResourceBoundleService;
import api.v1.KPI.Management.System.app.util.AppResponseUtil;

import api.v1.KPI.Management.System.offering.dto.core.OfferingResponseDTO;
import api.v1.KPI.Management.System.offering.dto.owner.OfferingOwnerCreateDTO;
import api.v1.KPI.Management.System.offering.dto.owner.OfferingOwnerUpdateDTO;
import api.v1.KPI.Management.System.offering.entity.OfferingEntity;
import api.v1.KPI.Management.System.offering.mapper.OfferingMapper;
import api.v1.KPI.Management.System.offering.mapper.OfferingOwnerMapper;
import api.v1.KPI.Management.System.offering.service.OfferingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OfferingOwnerService extends OfferingService {
    private final OfferingMapper offeringMapper;
    private final OfferingOwnerMapper offeringOwnerMapper;
    private final ResourceBoundleService boundleService;

    public OfferingOwnerService(OfferingMapper offeringMapper,
                                OfferingOwnerMapper offeringOwnerMapper,
                                ResourceBoundleService boundleService) {
        this.offeringMapper = offeringMapper;
        this.offeringOwnerMapper = offeringOwnerMapper;
        this.boundleService = boundleService;
    }

    @Transactional
    public OfferingResponseDTO ownerCreate(OfferingOwnerCreateDTO dto, AppLanguage lang){
        OfferingEntity entity = offeringOwnerMapper.toCreatedEntity(dto);
        return offeringMapper.toResponseDTO(create(entity));
    }

    @Transactional
    public AppResponse<String> ownerUpdate(OfferingOwnerUpdateDTO dto, AppLanguage lang){
        OfferingEntity entity = offeringOwnerMapper.toUpdatedEntity(dto);
        boolean updated = update(entity, lang);
        String messageKey = updated ? "category.update.completed.successfully" : "category.update.failed";
        return new AppResponse<>(boundleService.getMessage(messageKey, lang));
    }

    public Page<OfferingResponseDTO> getAllPage(int page, Integer size, AppLanguage lang) {
        int pageSize = (size == null || size <= 0) ? 10 : size;
        Pageable pageable = PageRequest.of(page, pageSize);
        return findAllPage(pageable).map(offeringMapper::toResponseDTO);
    }

}
