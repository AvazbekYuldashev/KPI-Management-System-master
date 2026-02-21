package api.v1.KPI.Management.System.offering.dto.manager;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfferingManagerUpdateDTO {
    private String id;
    private String title;
    private String description;
    private Float kpiBall;
    private Long deadline;
    private String categoryId;
    private String buildingId;
}
