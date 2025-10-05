package api.v1.KPI.Management.System.department.dto.owner;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentOwnerUpdateDTO {
    private String title;               /// Name
    private String description;         /// Description
    private String chiefId;             /// Department head ID
}
