package api.v1.KPI.Management.System.building.service.helper;

import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.app.service.ResourceBoundleService;
import api.v1.KPI.Management.System.building.entity.BuildingEntity;
import api.v1.KPI.Management.System.building.repoisotry.BuildingHelperRepository;
import api.v1.KPI.Management.System.exception.exps.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuildingHelperService {
    private final BuildingHelperRepository buildingHelperRepository;
    private final ResourceBoundleService boundleService;

    public BuildingHelperService(BuildingHelperRepository buildingHelperRepository,
                                 ResourceBoundleService boundleService) {
        this.buildingHelperRepository = buildingHelperRepository;
        this.boundleService = boundleService;
    }

    public BuildingEntity findById(String buildingId, AppLanguage lang) {
        return buildingHelperRepository.findById(buildingId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        boundleService.getMessage("building.not.found", lang)
                ));
    }
}
