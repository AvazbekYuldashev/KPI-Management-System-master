package api.v1.KPI.Management.System.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApplicationResponseDTO {
    private String id;
    private String title;
    private String description;
    private String sendProfileId;
    private String buildingId;
    private String acceptorProfileId;
    private String status;
    private Boolean visible;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
