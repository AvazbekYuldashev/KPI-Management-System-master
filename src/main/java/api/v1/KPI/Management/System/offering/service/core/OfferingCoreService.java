package api.v1.KPI.Management.System.offering.service.core;

import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.building.dto.core.BuildingResponseDTO;
import api.v1.KPI.Management.System.building.entity.BuildingEntity;
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
        OfferingEntity entity = findById(id);
        if (entity != null) return offeringMapper.toResponseDTO(entity);
        throw new ResourceNotFoundException("Offering Not Found");
    }

    public OfferingResponseDTO getByTitle(String title, AppLanguage lang) {
        OfferingEntity entity = findByTitle(title);
        if (entity != null) return offeringMapper.toResponseDTO(entity);
        throw new ResourceNotFoundException("Offering Not Found");
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


    public Page<OfferingResponseDTO> getAllPageByCategoryId(String id, int page, Integer size, AppLanguage lang) {
        Pageable pageable = PageRequest.of(page, size);

        // Bazadan sahifa bo‘yicha ma'lumotlarni olish
        Page<OfferingEntity> entitiesPage = findAllPageByCategoryId(id, pageable);

        // Entity → DTO map qilish
        List<OfferingResponseDTO> dtoList = entitiesPage.getContent().stream()
                .map(entity -> {return offeringMapper.toResponseDTO(entity);}).toList();

        // PageImpl orqali sahifa va pagination ma’lumotlarini saqlab DTO qaytarish
        return new PageImpl<>(dtoList, pageable, entitiesPage.getTotalElements());

    }


}
