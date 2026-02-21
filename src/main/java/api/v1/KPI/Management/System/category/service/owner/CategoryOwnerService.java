package api.v1.KPI.Management.System.category.service.owner;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.app.util.AppResponseUtil;
import api.v1.KPI.Management.System.building.dto.core.BuildingResponseDTO;
import api.v1.KPI.Management.System.building.entity.BuildingEntity;
import api.v1.KPI.Management.System.category.dto.core.CategoryResponseDTO;
import api.v1.KPI.Management.System.category.dto.manager.CategoryManagerCreateDTO;
import api.v1.KPI.Management.System.category.dto.owner.CategoryCreateDTO;
import api.v1.KPI.Management.System.category.dto.owner.CategoryUpdateDTO;
import api.v1.KPI.Management.System.category.entity.CategoryEntity;
import api.v1.KPI.Management.System.category.mapper.CategoryMapper;
import api.v1.KPI.Management.System.category.mapper.CategoryOwnerMapper;
import api.v1.KPI.Management.System.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryOwnerService extends CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private CategoryOwnerMapper categoryOwnerMapper;

    public CategoryResponseDTO ownerCreate(CategoryCreateDTO dto, AppLanguage lang){
        CategoryEntity entity = categoryOwnerMapper.toCreatedEntity(dto);
        return categoryMapper.toResponseDTO(create(entity));
    }

    public AppResponse<String> ownerUpdate(CategoryUpdateDTO dto, AppLanguage lang){
        CategoryEntity entity = categoryOwnerMapper.toUpdatedEntity(dto);
        return AppResponseUtil.chek(update(entity, lang));
    }

    public Page<CategoryResponseDTO> getAllPage(int page, Integer size, AppLanguage lang) {
        Pageable pageable = PageRequest.of(page, size);

        // Bazadan sahifa bo‘yicha ma'lumotlarni olish
        Page<CategoryEntity> entitiesPage = findAllPage(pageable);

        // Entity → DTO map qilish
        List<CategoryResponseDTO> dtoList = entitiesPage.getContent().stream()
                .map(entity -> {return categoryMapper.toResponseDTO(entity);}).toList();

        // PageImpl orqali sahifa va pagination ma’lumotlarini saqlab DTO qaytarish
        return new PageImpl<>(dtoList, pageable, entitiesPage.getTotalElements());
    }


}
