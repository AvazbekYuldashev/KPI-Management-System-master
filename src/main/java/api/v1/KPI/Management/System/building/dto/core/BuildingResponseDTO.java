package api.v1.KPI.Management.System.building.dto.core;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BuildingResponseDTO {
    private String id;
    private String title;
    private String description;
    private String chiefId;
    private String departmentId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private Boolean visible;


}
