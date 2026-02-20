package api.v1.KPI.Management.System.department.dto.manager;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentManagerUpdateDTO {
    private String id;
    /**
     * Name/title of the department
     */
    private String title;

    /**
     * Description of the department
     */
    private String description;

}
