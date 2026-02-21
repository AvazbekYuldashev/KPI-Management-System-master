package api.v1.KPI.Management.System.offering.service.manager;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.app.util.AppResponseUtil;
import api.v1.KPI.Management.System.offering.dto.core.OfferingResponseDTO;
import api.v1.KPI.Management.System.offering.dto.manager.OfferingManagerCreateDTO;
import api.v1.KPI.Management.System.offering.dto.manager.OfferingManagerUpdateDTO;
import api.v1.KPI.Management.System.offering.entity.OfferingEntity;
import api.v1.KPI.Management.System.offering.mapper.OfferingMapper;
import api.v1.KPI.Management.System.offering.service.OfferingService;
import api.v1.KPI.Management.System.security.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferingManagerService extends OfferingService {
    @Autowired
    private OfferingMapper offeringMapper;

    public OfferingResponseDTO managerCreate(OfferingManagerCreateDTO dto, AppLanguage lang) {
        OfferingEntity entity = offeringMapper.toManagerCreatedEntity(dto);
        entity.setDepartmentId(SpringSecurityUtil.getCurrentUserDepartmentId());
        return offeringMapper.toResponseDTO(create(entity));
    }

    public AppResponse<String> managerUpdate(OfferingManagerUpdateDTO dto, AppLanguage lang){
        OfferingEntity offering = findById(dto.getId());
        if (!offering.getDepartmentId().equals(SpringSecurityUtil.getCurrentUserDepartmentId())){
            throw new AuthorizationDeniedException("ruxsat yoq");
        }
        OfferingEntity entity = offeringMapper.toManagerUpdatedEntity(dto);
        return AppResponseUtil.chek(update(entity, lang));
    }

    public Page<OfferingResponseDTO> getAllPage(int page, Integer size, AppLanguage lang) {
        Pageable pageable = PageRequest.of(page, size);

        // Bazadan sahifa bo‘yicha ma'lumotlarni olish
        Page<OfferingEntity> entitiesPage = findAllPageByDepartmentIdAndVisibleTrue(SpringSecurityUtil.getCurrentUserDepartmentId(), pageable);

        // Entity → DTO map qilish
        List<OfferingResponseDTO> dtoList = entitiesPage.getContent().stream()
                .map(entity -> {return offeringMapper.toResponseDTO(entity);}).toList();

        // PageImpl orqali sahifa va pagination ma’lumotlarini saqlab DTO qaytarish
        return new PageImpl<>(dtoList, pageable, entitiesPage.getTotalElements());
    }

}
