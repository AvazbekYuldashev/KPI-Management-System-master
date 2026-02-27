package api.v1.KPI.Management.System.profile.service.helper;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.app.service.ResourceBoundleService;
import api.v1.KPI.Management.System.profile.repository.ProfileHelperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProfileHelperService {
    @Autowired
    private ProfileHelperRepository profileHelperRepository;
    @Autowired
    private ResourceBoundleService boundleService;

    public AppResponse<String> changeBuilding(String id, String departmentId, String buildingId, AppLanguage lang) {
        profileHelperRepository.changebuilding(id, departmentId, buildingId,  Boolean.TRUE, LocalDateTime.now());
        return new AppResponse<>(boundleService.getMessage("update.successfully.completed",lang));
    }

    public AppResponse<String> changeDepartmentId(String departmentId, String userId, AppLanguage lang) {
        profileHelperRepository.changeDepartmentId(userId, departmentId, Boolean.TRUE, LocalDateTime.now());
        return new AppResponse<>(boundleService.getMessage("update.successfully.completed",lang));
    }

}
