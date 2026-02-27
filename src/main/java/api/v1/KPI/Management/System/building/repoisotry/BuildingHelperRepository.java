package api.v1.KPI.Management.System.building.repoisotry;

import api.v1.KPI.Management.System.building.entity.BuildingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingHelperRepository extends JpaRepository<BuildingEntity, String> {
}
