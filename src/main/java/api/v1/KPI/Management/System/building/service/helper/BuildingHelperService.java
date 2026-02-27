package api.v1.KPI.Management.System.building.service.helper;

import api.v1.KPI.Management.System.building.entity.BuildingEntity;
import api.v1.KPI.Management.System.building.repoisotry.BuildingHelperRepository;
import api.v1.KPI.Management.System.exception.exps.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuildingHelperService {
    @Autowired
    private BuildingHelperRepository buildingHelperRepository;

    public BuildingEntity findById(String buildingId) {
        Optional<BuildingEntity> optional = buildingHelperRepository.findById(buildingId);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException(buildingId);
        }
        return optional.get();
    }
}
