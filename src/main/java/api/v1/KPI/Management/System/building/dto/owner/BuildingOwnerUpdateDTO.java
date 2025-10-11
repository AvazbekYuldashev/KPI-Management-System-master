package api.v1.KPI.Management.System.building.dto.owner;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuildingOwnerUpdateDTO {
    private String id;
    private String title;
    private String description;
    private String chiefId;
    private String departmentId;
}
