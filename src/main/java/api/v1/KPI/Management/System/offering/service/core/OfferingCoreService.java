package api.v1.KPI.Management.System.offering.service.core;

import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.exception.exps.ResourceNotFoundException;
import api.v1.KPI.Management.System.offering.dto.core.OfferingResponseDTO;
import api.v1.KPI.Management.System.offering.entity.OfferingEntity;
import api.v1.KPI.Management.System.offering.mapper.OfferingMapper;
import api.v1.KPI.Management.System.offering.service.OfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferingCoreService extends OfferingService {
    @Autowired
    private OfferingMapper offeringMapper;
    public OfferingResponseDTO getById(String id, AppLanguage lang){
        OfferingEntity entity = findById(id);
        if (entity != null) return offeringMapper.toResponseDTO(entity);
        throw new ResourceNotFoundException("Offering Not Found");
    }

    public OfferingResponseDTO getByTitle(String title, AppLanguage lang) {
        OfferingEntity entity = findByTitle(title);
        if (entity != null) return offeringMapper.toResponseDTO(entity);
        throw new ResourceNotFoundException("Offering Not Found");
    }
}
