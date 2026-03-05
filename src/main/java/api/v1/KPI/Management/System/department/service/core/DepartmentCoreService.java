package api.v1.KPI.Management.System.department.service.core;

import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.app.service.ResourceBoundleService;
import api.v1.KPI.Management.System.department.dto.core.DepartmentResponseDTO;
import api.v1.KPI.Management.System.department.entity.DepartmentEntity;
import api.v1.KPI.Management.System.department.mapper.DepartmentMapper;
import api.v1.KPI.Management.System.department.service.DepartmentService;
import api.v1.KPI.Management.System.exception.exps.ResourceNotFoundException;
import api.v1.KPI.Management.System.security.util.SpringSecurityUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DepartmentCoreService extends DepartmentService {
    private final DepartmentMapper departmentMapper;
    private final ResourceBoundleService boundleService;

    public DepartmentCoreService(DepartmentMapper departmentMapper,
                                 ResourceBoundleService boundleService) {
        this.departmentMapper = departmentMapper;
        this.boundleService = boundleService;
    }

    public DepartmentResponseDTO getById(String id, AppLanguage lang){
        DepartmentEntity entity = findById(id);
        return mapOrThrow(entity, lang);
    }

    public DepartmentResponseDTO getByTitle(String title, AppLanguage lang) {
        DepartmentEntity entity = findByTitle(title);
        return mapOrThrow(entity, lang);
    }

    public DepartmentResponseDTO getByMyId(AppLanguage lang) {
        DepartmentEntity entity = findById(SpringSecurityUtil.getCurrentUserDepartmentId());
        return mapOrThrow(entity, lang);

    }

    public Page<DepartmentResponseDTO> getAllPage(int page, Integer size, AppLanguage lang) {
        Pageable pageable = PageRequest.of(page, size);
        return findAllPage(pageable).map(departmentMapper::toResponseDTO);
    }

    private DepartmentResponseDTO mapOrThrow(DepartmentEntity entity, AppLanguage lang) {
        if (entity == null) {
            throw new ResourceNotFoundException(
                    boundleService.getMessage("department.not.found", lang)
            );
        }
        return departmentMapper.toResponseDTO(entity);
    }
}
