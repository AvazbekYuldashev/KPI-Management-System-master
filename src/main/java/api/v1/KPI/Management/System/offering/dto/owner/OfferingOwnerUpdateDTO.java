package api.v1.KPI.Management.System.offering.dto.owner;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfferingOwnerUpdateDTO {
    private String title;
    private String description;
    private Float kpiBall;
    private String categoryId;
}
