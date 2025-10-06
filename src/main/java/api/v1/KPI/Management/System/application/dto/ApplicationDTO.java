package api.v1.KPI.Management.System.application.dto;

import api.v1.KPI.Management.System.application.enums.ApplicationStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApplicationDTO {
    private String id;
    private String title;
    private String description;
    private String sendProfileId;
    private String buildingId;
    private String acceptorProfileId;
    private ApplicationStatus status;
    private Boolean visible;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
