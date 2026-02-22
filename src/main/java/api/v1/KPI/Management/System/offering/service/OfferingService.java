package api.v1.KPI.Management.System.offering.service;

import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.building.entity.BuildingEntity;
import api.v1.KPI.Management.System.exception.exps.ResourceNotFoundException;
import api.v1.KPI.Management.System.offering.dto.core.OfferingResponseDTO;
import api.v1.KPI.Management.System.offering.entity.OfferingEntity;
import api.v1.KPI.Management.System.offering.repository.OfferingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OfferingService {
    @Autowired
    private OfferingRepository offeringRepository;


    public OfferingEntity create(OfferingEntity entity){return offeringRepository.save(entity);}
    public OfferingEntity findByIdAndVisibleTrue(String id){return offeringRepository.findByIdAndVisibleTrue(id).get();}
    public Page<OfferingEntity> findAllPage(Pageable pageable) {
        return offeringRepository.findAllPage(pageable);
    }
    public Page<OfferingEntity> findAllByDepartmentIdAndVisibleTruePage(String departmentId, Pageable pageable) {return offeringRepository.findAllByDepartmentIdAndVisibleTruePage(departmentId, pageable);}
    public Page<OfferingEntity> finAllByBuildingIdPage(String id, Pageable pageable) {return offeringRepository.finAllByBuildingIdPage(id, pageable);}
    public Page<OfferingEntity> findAllPageByCategoryId(String id, Pageable pageable) {return offeringRepository.findAllPageByCategoryId(id, pageable);}
    public Boolean update(OfferingEntity entity, AppLanguage lang){
        OfferingEntity offering = findByIdAndVisibleTrue(entity.getId());
        if (offering == null) throw new ResourceNotFoundException("Offering Not Found");
        return offeringRepository.update(entity) > 0;
    }

}
