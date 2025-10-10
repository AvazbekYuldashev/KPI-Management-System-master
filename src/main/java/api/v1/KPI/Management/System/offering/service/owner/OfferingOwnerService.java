package api.v1.KPI.Management.System.offering.service.owner;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.offering.dto.OfferingResponseDTO;
import api.v1.KPI.Management.System.offering.dto.admin.OfferingAdminCreateDTO;
import api.v1.KPI.Management.System.offering.dto.admin.OfferingAdminUpdateDTO;
import api.v1.KPI.Management.System.offering.mapper.OfferingMapper;
import api.v1.KPI.Management.System.offering.service.OfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

@Service
public class OfferingOwnerService extends OfferingService {
    @Autowired
    private OfferingMapper offeringMapper;

    public OfferingResponseDTO adminCreate(OfferingAdminCreateDTO dto) {
        return offeringMapper.toResponseDTO(create(offeringMapper.toAdminCreateEntity(dto)));
    }

    public OfferingResponseDTO adminGetById(String id) {
        return offeringMapper.toResponseDTO(findById(id));
    }

    public PageImpl<OfferingResponseDTO> adminGetAll(int page, int size) {
        return getAll(page, size);
    }

    public AppResponse<String> adminDelete(String id) {
        return deleteById(id);
    }

    public AppResponse<String> adminUpdate(String id, OfferingAdminUpdateDTO dto) {
        return updateDetail(id, dto);
    }
}
