package api.v1.KPI.Management.System.department.dto.core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentUpdateDTO {
    /**
     * Name/title of the department
     */
    private String title;

    /**
     * Description of the department
     */
    private String description;

    /**
     * ID of the department head (chief)
     */
    private String chiefId;
}
