package api.v1.KPI.Management.System.category.dto.owner;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CategoryUpdateDTO {
    private String id;
    private String title;
    private String description;
    private String departmentId;
    private String buildingId;
}
