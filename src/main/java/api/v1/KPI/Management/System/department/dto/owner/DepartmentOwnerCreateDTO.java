package api.v1.KPI.Management.System.department.dto.owner;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentOwnerCreateDTO {
    private String title;               /// Name
    private String description;         /// Description
    private String chiefId;             /// Department head ID
}
