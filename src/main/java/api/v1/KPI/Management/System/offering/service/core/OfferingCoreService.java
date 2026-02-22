package api.v1.KPI.Management.System.offering.service.core;

import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.building.dto.core.BuildingResponseDTO;
import api.v1.KPI.Management.System.building.entity.BuildingEntity;
import api.v1.KPI.Management.System.building.mapper.BuildingMapper;
import api.v1.KPI.Management.System.exception.exps.ResourceNotFoundException;
import api.v1.KPI.Management.System.offering.dto.core.OfferingResponseDTO;
import api.v1.KPI.Management.System.offering.entity.OfferingEntity;
import api.v1.KPI.Management.System.offering.mapper.OfferingMapper;
import api.v1.KPI.Management.System.offering.service.OfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferingCoreService extends OfferingService {
    @Autowired
    private OfferingMapper offeringMapper;

    public OfferingResponseDTO getById(String id, AppLanguage lang){
        OfferingEntity entity = findByIdAndVisibleTrue(id);
        if (entity != null) return offeringMapper.toResponseDTO(entity);
        throw new ResourceNotFoundException("Offering Not Found");
    }

    public Page<OfferingResponseDTO> getAllByDepartmentIdPage(String id, int page, Integer size, AppLanguage lang) {
        Pageable pageable = PageRequest.of(page, size);
        return findAllByDepartmentIdAndVisibleTruePage(id, pageable).map(entity -> offeringMapper.toResponseDTO(entity));
    }

    public Page<OfferingResponseDTO> getAllByBuildingIdPage(String id, int page, Integer size, AppLanguage lang) {
        Pageable pageable = PageRequest.of(page, size);
        return finAllByBuildingIdPage(id, pageable).map(entity -> offeringMapper.toResponseDTO(entity));
    }

    public Page<OfferingResponseDTO> getAllPageByCategoryId(String id, int page, Integer size, AppLanguage lang) {
        Pageable pageable = PageRequest.of(page, size);
        return findAllPageByCategoryId(id, pageable).map(entity -> offeringMapper.toResponseDTO(entity));


    }

}
