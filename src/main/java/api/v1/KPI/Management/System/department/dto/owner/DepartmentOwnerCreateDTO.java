package api.v1.KPI.Management.System.department.dto.owner;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentOwnerCreateDTO {
    private String title;
    private String description;
    private String chiefId;
}
