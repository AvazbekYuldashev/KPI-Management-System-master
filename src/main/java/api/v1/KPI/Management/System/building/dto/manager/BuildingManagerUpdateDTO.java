package api.v1.KPI.Management.System.building.dto.manager;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuildingManagerUpdateDTO {
    private String id;
    private String title;
    private String description;
    private String chiefId;
}
