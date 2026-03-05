package api.v1.KPI.Management.System.department.service.owner;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.app.service.ResourceBoundleService;
import api.v1.KPI.Management.System.department.dto.owner.DepartmentOwnerCreateDTO;
import api.v1.KPI.Management.System.department.dto.owner.DepartmentOwnerUpdateDTO;
import api.v1.KPI.Management.System.department.dto.core.DepartmentResponseDTO;
import api.v1.KPI.Management.System.department.entity.DepartmentEntity;
import api.v1.KPI.Management.System.department.mapper.DepartmentMapper;
import api.v1.KPI.Management.System.department.mapper.DepartmentOwnerMapper;
import api.v1.KPI.Management.System.department.service.DepartmentService;
import api.v1.KPI.Management.System.profile.service.helper.ProfileHelperService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentOwnerService extends DepartmentService {

    private final DepartmentOwnerMapper departmentOwnerMapper;
    private final DepartmentMapper departmentMapper;
    private final ProfileHelperService profileHelperService;
    private final ResourceBoundleService boundleService;

    public DepartmentOwnerService(DepartmentOwnerMapper departmentOwnerMapper,
                                  DepartmentMapper departmentMapper,
                                  ProfileHelperService profileHelperService,
                                  ResourceBoundleService boundleService) {
        this.departmentOwnerMapper = departmentOwnerMapper;
        this.departmentMapper = departmentMapper;
        this.profileHelperService = profileHelperService;
        this.boundleService = boundleService;
    }

    public DepartmentResponseDTO ownerCreate(DepartmentOwnerCreateDTO dto, AppLanguage lang){
        DepartmentEntity entity = departmentOwnerMapper.toCreatedEntity(dto);
        DepartmentEntity save = create(entity);
        Optional.ofNullable(dto.getChiefId())
                .ifPresent(chiefId -> profileHelperService.changeDepartmentId(entity.getId(), chiefId, lang));
        return departmentMapper.toResponseDTO(save);
    }

    public AppResponse<String> ownerUpdate(DepartmentOwnerUpdateDTO dto, AppLanguage lang){
        DepartmentEntity entity = departmentOwnerMapper.toUpdatedEntity(dto);
        Optional.ofNullable(dto.getChiefId())
                .ifPresent(chiefId -> profileHelperService.changeDepartmentId(entity.getId(), chiefId, lang));

        boolean updated = update(entity, lang);
        String messageKey = updated ? "department.update.completed.successfully" : "department.update.failed";
        return new AppResponse<>(boundleService.getMessage(messageKey, lang));
    }

    public Page<DepartmentResponseDTO> getAllPage(int page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return findAllPage(pageable).map(departmentMapper::toResponseDTO);
    }


}
