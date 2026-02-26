package api.v1.KPI.Management.System.kpi.controller.core;


import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.kpi.dto.core.KpiResponseDTO;
import api.v1.KPI.Management.System.kpi.service.core.KpiCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kpi-core")
public class KpiCoreController {
    @Autowired
    private KpiCoreService kpiCoreService;

    @GetMapping("/by-employee")
    public ResponseEntity<Page<KpiResponseDTO>> getEmployeeKpi(@PathVariable String id,
                                                               @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                               @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                               @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(kpiCoreService.getEmployeeKpi(id, getCurrentPage(page), size, lang));
    }
    public static int getCurrentPage(Integer page) {
        return (page != null && page > 0) ? page - 1 : 0;
    }

}
