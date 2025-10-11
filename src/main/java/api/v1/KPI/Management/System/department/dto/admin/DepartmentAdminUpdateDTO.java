package api.v1.KPI.Management.System.department.dto.admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentAdminUpdateDTO {
    private String id;
    private String title;
    private String description;
    private String chiefId;
}
