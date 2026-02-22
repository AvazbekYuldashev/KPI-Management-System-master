package api.v1.KPI.Management.System.offering.dto.owner;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfferingOwnerCreateDTO {
    private String title;
    private String description;
    private Float kpiBall;
    private Long deadline;
    private String categoryId;
    private String buildingId;
    private String departmentId;
}
