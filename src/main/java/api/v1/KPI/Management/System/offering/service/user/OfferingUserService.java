package api.v1.KPI.Management.System.offering.service.user;

import api.v1.KPI.Management.System.offering.dto.OfferingResponseDTO;
import api.v1.KPI.Management.System.offering.mapper.OfferingMapper;
import api.v1.KPI.Management.System.offering.service.OfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

@Service
public class OfferingUserService extends OfferingService {
    @Autowired
    private OfferingMapper offeringMapper;

    public OfferingResponseDTO userGetById(String id) {
        return offeringMapper.toResponseDTO(findById(id));
    }

    public PageImpl<OfferingResponseDTO> userGetAll(int page, int size) {
        return getAll(page, size);
    }

}
