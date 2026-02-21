package api.v1.KPI.Management.System.category.mapper;

import api.v1.KPI.Management.System.category.dto.manager.CategoryManagerCreateDTO;
import api.v1.KPI.Management.System.category.dto.manager.CategoryManagerUpdateDTO;
import api.v1.KPI.Management.System.category.entity.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryManagerMapper {
    public CategoryEntity toCreatedEntity(CategoryManagerCreateDTO dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setBuildingId(dto.getBuildingId());
        return entity;
    }

    public CategoryEntity toUpdatedEntity(CategoryManagerUpdateDTO dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setBuildingId(dto.getBuildingId());
        return entity;
    }
}
