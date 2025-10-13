package api.v1.KPI.Management.System.offering.service.owner;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.offering.dto.core.OfferingResponseDTO;
import api.v1.KPI.Management.System.offering.dto.owner.OfferingOwnerCreateDTO;
import api.v1.KPI.Management.System.offering.dto.owner.OfferingOwnerUpdateDTO;
import api.v1.KPI.Management.System.offering.mapper.OfferingMapper;
import api.v1.KPI.Management.System.offering.service.OfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferingOwnerService extends OfferingService {
    @Autowired
    private OfferingMapper offeringMapper;


    public OfferingResponseDTO createOwner(OfferingOwnerCreateDTO dto, String lang) {
        return offeringMapper.toResponseDTO(create(offeringMapper.toCreatedEntity(dto)));
    }

    public AppResponse<String> update(OfferingOwnerUpdateDTO dto, String lang) {
        return changeDetail(offeringMapper.toUpdatedEntity(dto));
    }

    public AppResponse<String> delete(String id, String lang) {
        return deleteById(id);
    }
}
