package api.v1.KPI.Management.System.building.mapper;

import api.v1.KPI.Management.System.building.dto.BuildingResponseDTO;
import api.v1.KPI.Management.System.building.dto.admin.BuildingAdminCreateDTO;
import api.v1.KPI.Management.System.building.dto.admin.BuildingAdminUpdateDTO;
import api.v1.KPI.Management.System.building.dto.owner.BuildingOwnerCreateDTO;
import api.v1.KPI.Management.System.building.dto.owner.BuildingOwnerUpdateDTO;
import api.v1.KPI.Management.System.building.entity.BuildingEntity;
import api.v1.KPI.Management.System.department.entity.DepartmentEntity;
import api.v1.KPI.Management.System.profile.entity.ProfileEntity;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BuildingMapper {

    public BuildingResponseDTO toResponseDTO(BuildingEntity entity) {
        BuildingResponseDTO dto = new BuildingResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setChiefId(entity.getChiefId());
        dto.setDepartmentId(entity.getDepartmentId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedDate(entity.getUpdatedDate());
        dto.setVisible(entity.getVisible());
        return dto;
    }

    public BuildingEntity toAdminCreateEntity(BuildingAdminCreateDTO dto) {
        BuildingEntity entity = new BuildingEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setChiefId(dto.getChiefId());
        entity.setDepartmentId(dto.getDepartmentId());
        return entity;
    }

    public BuildingEntity toEntity(BuildingOwnerCreateDTO dto) {
        BuildingEntity entity = new BuildingEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setChiefId(dto.getChiefId());
        entity.setDepartmentId(dto.getDepartmentId());
        return entity;
    }

    public BuildingEntity toEntity(BuildingAdminUpdateDTO dto) {
        BuildingEntity entity = new BuildingEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setChiefId(dto.getChiefId());
        entity.setDepartmentId(dto.getDepartmentId());
        return entity;
    }

    public BuildingEntity toEntity(BuildingOwnerUpdateDTO dto) {
        BuildingEntity entity = new BuildingEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setChiefId(dto.getChiefId());
        entity.setDepartmentId(dto.getDepartmentId());
        return entity;

    }
}
