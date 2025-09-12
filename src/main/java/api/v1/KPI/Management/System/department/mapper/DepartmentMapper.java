package api.v1.KPI.Management.System.department.mapper;

import api.v1.KPI.Management.System.department.dto.DepartmentResponseDTO;
import api.v1.KPI.Management.System.department.dto.admin.DepartmentAdminCreateDTO;
import api.v1.KPI.Management.System.department.entity.DepartmentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {

    public DepartmentEntity toAdminCreateEntity(DepartmentAdminCreateDTO dto) {
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
        return responseDTO;
    }

    public Page<DepartmentEntity> findAllPage(PageRequest of) {
    }
}
