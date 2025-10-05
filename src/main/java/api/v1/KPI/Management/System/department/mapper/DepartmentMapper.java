package api.v1.KPI.Management.System.department.mapper;

import api.v1.KPI.Management.System.department.dto.DepartmentResponseDTO;
import api.v1.KPI.Management.System.department.dto.owner.DepartmentOwnerCreateDTO;
import api.v1.KPI.Management.System.department.entity.DepartmentEntity;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {

    public DepartmentEntity toAdminCreateEntity(DepartmentOwnerCreateDTO dto) {
        DepartmentEntity entity = new DepartmentEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setChiefId(dto.getChiefId());
        return entity;
    }

    public DepartmentResponseDTO toResponseDTO(DepartmentEntity entity) {
        DepartmentResponseDTO responseDTO = new DepartmentResponseDTO();
        responseDTO.setId(entity.getId());
        responseDTO.setTitle(entity.getTitle());
        responseDTO.setDescription(entity.getDescription());
        responseDTO.setChiefId(entity.getChiefId());
        responseDTO.setCreatedDate(entity.getCreatedDate());
        responseDTO.setUpdatedDate(entity.getUpdatedDate());
        responseDTO.setVisible(entity.getVisible());
        return responseDTO;
    }

}
