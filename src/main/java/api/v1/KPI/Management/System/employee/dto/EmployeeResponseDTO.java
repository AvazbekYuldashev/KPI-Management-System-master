package api.v1.KPI.Management.System.employee.dto;

import api.v1.KPI.Management.System.attach.dto.AttachDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EmployeeResponseDTO {
    private String id;
    private String name;
    private String surname;
    private String username;
    private AttachDTO photo;
    private LocalDateTime createdDate;
    private Boolean inProgress;
}
