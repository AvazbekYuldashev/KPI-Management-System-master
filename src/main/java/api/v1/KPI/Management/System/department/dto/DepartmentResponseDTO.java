package api.v1.KPI.Management.System.department.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) for returning department information
 * in API responses.
 * <p>
 * Contains department details such as ID, name, description,
 * head of department, visibility status, and audit timestamps.
 */

@Getter
@Setter
/// Information about the returned department
public class DepartmentResponseDTO {
    private String id;                  /// ID
    private String title;               /// Name
    private String description;         /// Description
    private String chiefId;             /// Department head ID
    private Boolean visible;            /// Visibility status
    private LocalDateTime createdDate;  /// Creation date and time
    private LocalDateTime updatedDate;  /// Last updated date and time
}
