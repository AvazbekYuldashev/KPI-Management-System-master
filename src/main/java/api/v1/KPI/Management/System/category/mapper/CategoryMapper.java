package api.v1.KPI.Management.System.category.mapper;

import api.v1.KPI.Management.System.category.dto.core.CategoryResponseDTO;
import api.v1.KPI.Management.System.category.dto.owner.CategoryCreateDTO;
import api.v1.KPI.Management.System.category.dto.owner.CategoryUpdateDTO;
import api.v1.KPI.Management.System.category.entity.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryEntity toCreatedEntity(CategoryCreateDTO dto){
        CategoryEntity entity = new CategoryEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setDepartmentId(dto.getDepartmentId());
        entity.setBuildingId(dto.getBuildingId());
        return entity;
    }

    public CategoryResponseDTO toResponseDTO(CategoryEntity entity){
        CategoryResponseDTO dto = new CategoryResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setDepartmentId(entity.getDepartmentId());
        dto.setBuildingId(entity.getBuildingId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedDate(entity.getUpdatedDate());
        dto.setVisible(entity.getVisible());
        return dto;
    }

    public CategoryEntity toUpdatedEntity(CategoryUpdateDTO dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setDepartmentId(dto.getDepartmentId());
        entity.setBuildingId(dto.getBuildingId());
        return entity;
    }
}
