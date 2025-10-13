package api.v1.KPI.Management.System.offering.service.core;

import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.offering.dto.core.OfferingResponseDTO;
import api.v1.KPI.Management.System.offering.mapper.OfferingMapper;
import api.v1.KPI.Management.System.offering.service.OfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class OfferingCoreService extends OfferingService {

    @Autowired
    private OfferingMapper offeringMapper;

    public OfferingResponseDTO getById(String id, AppLanguage lang) {
        return offeringMapper.toResponseDTO(findById(id));
    }

    public Page<OfferingResponseDTO> getAll(int page, Integer size, AppLanguage lang) {
        return findAll(page, size);
    }

    public Page<OfferingResponseDTO> getByCategoryId(String id, int page, Integer size, AppLanguage lang) {
        return findByCategoryId(id, page, size);
    }
}
