package api.v1.KPI.Management.System.offering.dto.owner;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OfferingOwnerCreateDTO {
    private String title;
    private String description;
    private Float kpiBall;
    private String categoryId;
    private String departmentId;
}
