package api.v1.KPI.Management.System.kpi.service.core;

import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.kpi.dto.core.KpiResponseDTO;
import api.v1.KPI.Management.System.kpi.service.KpiService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class KpiCoreService extends KpiService {

    public Page<KpiResponseDTO> getEmployeeKpi(String id, int page, Integer size, AppLanguage lang) {
        Pageable pageable = PageRequest.of(page, size);
        return findEmployeeKpi(id, pageable);
    }


}
