package api.v1.KPI.Management.System.department.service.helper;

import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.app.service.ResourceBoundleService;
import api.v1.KPI.Management.System.department.entity.DepartmentEntity;
import api.v1.KPI.Management.System.department.repository.DepartmentHelperRepository;
import api.v1.KPI.Management.System.exception.exps.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DepartmentHelperService {

    private final DepartmentHelperRepository departmentHelperRepository;
    private final ResourceBoundleService boundleService;

    public DepartmentHelperService(DepartmentHelperRepository departmentHelperRepository,
                                   ResourceBoundleService boundleService) {
        this.departmentHelperRepository = departmentHelperRepository;
        this.boundleService = boundleService;
    }

    public DepartmentEntity findById(String id,  AppLanguage lang) {
        return departmentHelperRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        boundleService.getMessage("department.not.found", lang)
                ));
    }
}
