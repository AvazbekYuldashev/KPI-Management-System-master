package api.v1.KPI.Management.System.building.service.admin;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.building.dto.BuildingResponseDTO;
import api.v1.KPI.Management.System.building.dto.admin.BuildingAdminCreateDTO;
import api.v1.KPI.Management.System.building.dto.admin.BuildingAdminUpdateDTO;
import api.v1.KPI.Management.System.building.entity.BuildingEntity;
import api.v1.KPI.Management.System.building.mapper.BuildingMapper;
import api.v1.KPI.Management.System.building.service.BuildingService;
import api.v1.KPI.Management.System.exception.exps.ResourceNotFoundException;
import api.v1.KPI.Management.System.security.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

@Service
public class BuildingAdminService extends BuildingService {
    @Autowired
    private BuildingMapper buildingMapper;
    public BuildingResponseDTO adminCreate(BuildingAdminCreateDTO dto) {
        String departmentId = SpringSecurityUtil.getCurrentUserDepartmentId();
        if (departmentId == null) {
            throw new ResourceNotFoundException("Department not found");
        }
        BuildingEntity entity = buildingMapper.toAdminCreateEntity(dto);
        entity.setDepartmentId(departmentId);
        return buildingMapper.toResponseDTO(create(entity));
    }

    public PageImpl<BuildingResponseDTO> adminGetAll(int page, int size) {
        return getAll(page, size);
    }

    public AppResponse<String> adminDelete(String id) {
        BuildingEntity entity = findById(id);
        if (!entity.getDepartmentId().equals(SpringSecurityUtil.getCurrentUserDepartmentId())) {
            throw new ResourceNotFoundException("Building not found");
        }
        return deleteById(id);
    }

    public AppResponse<String> adminUpdate(String id, BuildingAdminUpdateDTO dto) {
        BuildingEntity entity = findById(id);
        if (!entity.getDepartmentId().equals(SpringSecurityUtil.getCurrentUserDepartmentId())) {
            throw new ResourceNotFoundException("Building not found");
        }
        return updateDetail(id, dto);
    }

    //
}
