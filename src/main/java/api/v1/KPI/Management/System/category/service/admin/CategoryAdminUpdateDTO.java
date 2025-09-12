package api.v1.KPI.Management.System.category.service.admin;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CategoryAdminUpdateDTO {
    private String title;
    private String description;
    private String departmentId;
}
