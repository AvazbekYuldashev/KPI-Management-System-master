package api.v1.KPI.Management.System.building.dto.owner;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BuildingOwnerUpdateDTO {
    private String title;
    private String description;
    private String chiefId;
    private String departmentId;
}
