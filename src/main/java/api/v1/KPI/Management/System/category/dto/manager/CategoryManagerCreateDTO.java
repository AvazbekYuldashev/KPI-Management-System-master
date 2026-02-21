package api.v1.KPI.Management.System.category.dto.manager;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryManagerCreateDTO {
    private String title;
    private String description;
    private String buildingId;
}
