package api.v1.KPI.Management.System.application.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationUserUpdateDTO {
    private String title;
    private String description;
    private String buildingId;
}
