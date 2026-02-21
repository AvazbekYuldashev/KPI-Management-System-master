package api.v1.KPI.Management.System.category.dto.manager;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryManagerUpdateDTO {
    private String id;
    private String title;
    private String description;
    private String buildingId;
}
