package api.v1.KPI.Management.System.department.dto.core;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DepartmentResponseDTO {
    private String id;
    private String title;
    private String description;
    private String chiefId;
    private Boolean visible;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
