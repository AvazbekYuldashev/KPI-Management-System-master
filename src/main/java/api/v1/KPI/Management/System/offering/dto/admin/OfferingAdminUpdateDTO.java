package api.v1.KPI.Management.System.offering.dto.admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfferingAdminUpdateDTO {
    private String id;
    private String title;
    private String description;
    private Float kpiBall;
    private String categoryId;
}
