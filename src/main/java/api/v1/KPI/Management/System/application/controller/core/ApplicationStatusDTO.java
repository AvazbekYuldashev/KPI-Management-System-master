package api.v1.KPI.Management.System.application.controller.core;

import api.v1.KPI.Management.System.application.enums.ApplicationStatus;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ApplicationStatusDTO {
    private String id;
    private ApplicationStatus status;
}
