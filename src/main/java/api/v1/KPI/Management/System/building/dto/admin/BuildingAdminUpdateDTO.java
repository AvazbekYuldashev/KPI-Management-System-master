package api.v1.KPI.Management.System.building.dto.admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuildingAdminUpdateDTO {
    private String title;
    private String description;
    private String chiefId;
    private String departmentId;
}
