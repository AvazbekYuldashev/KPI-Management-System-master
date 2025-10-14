package api.v1.KPI.Management.System.offering.dto.core;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OfferingResponseDTO {
    private String id;
    private String title;
    private String description;
    private Float kpiBall;
    private String categoryId;
    private String departmentId;
    private Boolean visible;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
