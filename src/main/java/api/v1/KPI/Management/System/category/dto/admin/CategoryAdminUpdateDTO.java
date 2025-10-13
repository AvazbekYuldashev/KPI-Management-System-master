package api.v1.KPI.Management.System.category.dto.admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryAdminUpdateDTO {
    private String id;
    private String title;
    private String description;
    private String departmentId;
}
