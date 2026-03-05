package api.v1.KPI.Management.System.category.service.owner;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.app.service.ResourceBoundleService;
import api.v1.KPI.Management.System.category.dto.core.CategoryResponseDTO;
import api.v1.KPI.Management.System.category.dto.owner.CategoryOwnerCreateDTO;
import api.v1.KPI.Management.System.category.dto.owner.CategoryOwnerUpdateDTO;
import api.v1.KPI.Management.System.category.entity.CategoryEntity;
import api.v1.KPI.Management.System.category.mapper.CategoryMapper;
import api.v1.KPI.Management.System.category.mapper.CategoryOwnerMapper;
import api.v1.KPI.Management.System.category.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryOwnerService extends CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoryOwnerMapper categoryOwnerMapper;
    private final ResourceBoundleService boundleService;

    public CategoryOwnerService(CategoryMapper categoryMapper,
                                CategoryOwnerMapper categoryOwnerMapper,
                                ResourceBoundleService boundleService) {
        this.categoryMapper = categoryMapper;
        this.categoryOwnerMapper = categoryOwnerMapper;
        this.boundleService = boundleService;
    }

    public CategoryResponseDTO ownerCreate(CategoryOwnerCreateDTO dto, AppLanguage lang){
        CategoryEntity entity = categoryOwnerMapper.toCreatedEntity(dto);
        CategoryEntity created = create(entity);
        return categoryMapper.toResponseDTO(created);
    }

    public AppResponse<String> ownerUpdate(CategoryOwnerUpdateDTO dto, AppLanguage lang){
        CategoryEntity entity = categoryOwnerMapper.toUpdatedEntity(dto);
        boolean updated = update(entity, lang);
        String messageKey = updated ? "category.update.completed.successfully" : "category.update.failed";
        return new AppResponse<>(boundleService.getMessage(messageKey, lang));

    }

    public Page<CategoryResponseDTO> getAllPage(int page, Integer size, AppLanguage lang) {
        Pageable pageable = PageRequest.of(page, size);
        return findAllByVisibleTruePage(pageable).map(categoryMapper::toResponseDTO);
    }


}
