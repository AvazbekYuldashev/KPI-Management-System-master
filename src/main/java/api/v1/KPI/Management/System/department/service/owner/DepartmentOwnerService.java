package api.v1.KPI.Management.System.department.service.owner;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.app.util.AppResponseUtil;
import api.v1.KPI.Management.System.department.dto.owner.DepartmentOwnerCreateDTO;
import api.v1.KPI.Management.System.department.dto.owner.DepartmentOwnerUpdateDTO;
import api.v1.KPI.Management.System.department.dto.core.DepartmentResponseDTO;
import api.v1.KPI.Management.System.department.entity.DepartmentEntity;
import api.v1.KPI.Management.System.department.mapper.DepartmentMapper;
import api.v1.KPI.Management.System.department.mapper.DepartmentOwnerMapper;
import api.v1.KPI.Management.System.department.service.DepartmentService;
import api.v1.KPI.Management.System.profile.service.owner.ProfileOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentOwnerService extends DepartmentService {
    @Autowired
    private DepartmentOwnerMapper departmentOwnerMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private ProfileOwnerService profileService;

    public DepartmentResponseDTO ownerCreate(DepartmentOwnerCreateDTO dto, AppLanguage lang){
        DepartmentEntity entity = departmentOwnerMapper.toCreatedEntity(dto);
        DepartmentEntity save = create(entity);
        if (dto.getChiefId() != null) {
            profileService.updateDepartment(dto.getChiefId(), entity.getId(), lang);
        }
        return departmentMapper.toResponseDTO(save);
    }

    public AppResponse<String> ownerUpdate(DepartmentOwnerUpdateDTO dto, AppLanguage lang){
        DepartmentEntity entity = departmentOwnerMapper.toUpdatedEntity(dto);
        if (dto.getChiefId() != null) {
            profileService.updateEmployee(dto.getChiefId(), dto.getId(), null, lang);
        }
        return AppResponseUtil.chek(update(entity, lang));
    }


    public Page<DepartmentResponseDTO> getAllPage(int page, Integer size, AppLanguage lang) {
        Pageable pageable = PageRequest.of(page, size);
        return findAllPage(pageable).map(entity -> departmentMapper.toResponseDTO(entity));
    }


}
