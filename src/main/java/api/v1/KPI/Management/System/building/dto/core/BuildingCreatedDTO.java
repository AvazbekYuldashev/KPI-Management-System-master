package api.v1.KPI.Management.System.building.dto.core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuildingCreatedDTO {
    private String title;
    private String description;
    private String chiefId;
    private String departmentId;
}
