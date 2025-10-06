package api.v1.KPI.Management.System.application.dto.admin;

import api.v1.KPI.Management.System.application.enums.ApplicationStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationAdminUpdateDTO {
    private String title;
    private String description;
    private String sendProfileId;
    private String buildingId;
    private String acceptorProfileId;
    private ApplicationStatus status;
}
