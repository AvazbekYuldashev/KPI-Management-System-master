package api.v1.KPI.Management.System.kpi.service;

import api.v1.KPI.Management.System.application.service.ApplicationService;
import api.v1.KPI.Management.System.kpi.dto.core.KpiResponseDTO;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Server
public class KpiService {
    @Autowired
    private ApplicationService applicationService;

    public Page<KpiResponseDTO> findEmployeeKpi(String id, Pageable pageable) {
        return applicationService.getEmployeeKpi(id, pageable);
    }
}
