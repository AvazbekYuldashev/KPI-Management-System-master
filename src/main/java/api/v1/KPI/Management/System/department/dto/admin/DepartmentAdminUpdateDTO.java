package api.v1.KPI.Management.System.department.dto.admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentAdminUpdateDTO {
    private String title;               /// Name
    private String description;         /// Description
    private String chiefId;             /// Department head ID
}
