package api.v1.KPI.Management.System.kpi.dto.core;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KpiResponseDTO {
    private String employeeId;
    private String employeeName;
    private String employeeSurname;
    private Long acceptedTaskCount;
    private Long rejectedTaskCount;
    private Long completedTaskCount;
    private Float totalScore;
}
