package api.v1.KPI.Management.System.offering.service.owner;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.app.util.AppResponseUtil;

import api.v1.KPI.Management.System.department.dto.core.DepartmentResponseDTO;
import api.v1.KPI.Management.System.department.entity.DepartmentEntity;
import api.v1.KPI.Management.System.offering.dto.core.OfferingResponseDTO;
import api.v1.KPI.Management.System.offering.dto.owner.OfferingCreateDTO;
import api.v1.KPI.Management.System.offering.dto.owner.OfferingUpdateDTO;
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

    public Page<OfferingResponseDTO> getAllPage(int page, Integer size, AppLanguage lang) {
        Pageable pageable = PageRequest.of(page, size);

        // Bazadan sahifa bo‘yicha ma'lumotlarni olish
        Page<OfferingEntity> entitiesPage = findAllPage(pageable);

        // Entity → DTO map qilish
        List<OfferingResponseDTO> dtoList = entitiesPage.getContent().stream()
                .map(entity -> {return offeringMapper.toResponseDTO(entity);}).toList();

        // PageImpl orqali sahifa va pagination ma’lumotlarini saqlab DTO qaytarish
        return new PageImpl<>(dtoList, pageable, entitiesPage.getTotalElements());
    }


}
