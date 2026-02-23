package api.v1.KPI.Management.System.category.mapper;

import api.v1.KPI.Management.System.category.dto.owner.CategoryOwnerCreateDTO;
import api.v1.KPI.Management.System.category.dto.owner.CategoryOwnerUpdateDTO;
import api.v1.KPI.Management.System.category.entity.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryOwnerMapper {
    public CategoryEntity toCreatedEntity(CategoryOwnerCreateDTO dto){
        CategoryEntity entity = new CategoryEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setDepartmentId(dto.getDepartmentId());
        entity.setBuildingId(dto.getBuildingId());
        return entity;
    }


    public CategoryEntity toUpdatedEntity(CategoryOwnerUpdateDTO dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setDepartmentId(dto.getDepartmentId());
        entity.setBuildingId(dto.getBuildingId());
        return entity;
    }
}
