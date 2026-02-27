package api.v1.KPI.Management.System.application.service;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.app.util.AppResponseUtil;
import api.v1.KPI.Management.System.application.dto.core.ApplicationGetterDTO;
import api.v1.KPI.Management.System.application.dto.core.ApplicationStatusDTO;
import api.v1.KPI.Management.System.application.dto.core.ApplicationResponseDTO;
import api.v1.KPI.Management.System.application.dto.manager.ApplicationFilterDTO;
import api.v1.KPI.Management.System.application.entity.ApplicationEntity;
import api.v1.KPI.Management.System.application.repository.ApplicationCustomRepository;
import api.v1.KPI.Management.System.application.repository.ApplicationRepository;
import api.v1.KPI.Management.System.exception.exps.ResourceNotFoundException;
import api.v1.KPI.Management.System.kpi.dto.core.KpiResponseDTO;
import api.v1.KPI.Management.System.security.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class  ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private ApplicationCustomRepository applicationCustomRepository;

    public ApplicationEntity save(ApplicationEntity entity) {
        return applicationRepository.save(entity);
    }

    public ApplicationEntity findById(String id) {
        Optional<ApplicationEntity> optional = applicationRepository.findById(id);
        if (optional.isEmpty()){
            throw new ResourceNotFoundException("Application with id: " + id + " not found");
        }
        return optional.get();
    }

    public Page<ApplicationResponseDTO> ownerFilter(ApplicationFilterDTO dto, int page, Integer size, AppLanguage lang) {
        dto.setDepartmentId(SpringSecurityUtil.getCurrentUserDepartmentId());
        return applicationCustomRepository.filter(dto, page,size);
    }

    public AppResponse<String> changeStatus(ApplicationStatusDTO dto, AppLanguage lang) {
        findById(dto.getId());
        int effectedRow = applicationRepository.changeStatus(dto);
        return AppResponseUtil.chek(effectedRow > 0);
    }


    public Page<ApplicationEntity> findAllByMyIdAndVisibleTruePage(String userId, Pageable pageable) {
        return applicationRepository.findAllByMyIdAndVisibleTruePage(userId, pageable);
    }

    public Page<KpiResponseDTO> getEmployeeKpi(String id, Pageable pageable) {
        ApplicationGetterDTO dto = ApplicationGetterDTO.builder().acceptorProfileId(id).build();
        return applicationRepository.getEmployeeKpi(dto, pageable);
    }
}
