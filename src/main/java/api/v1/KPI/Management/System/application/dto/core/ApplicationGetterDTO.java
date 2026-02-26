package api.v1.KPI.Management.System.application.dto.core;

import api.v1.KPI.Management.System.application.enums.ApplicationStatus;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationGetterDTO {
    private String acceptorProfileId;           // xodim ID filter

    @Builder.Default
    private ApplicationStatus accepted = ApplicationStatus.IN_PROGRESS;

    @Builder.Default
    private ApplicationStatus rejected = ApplicationStatus.DENIED;

    @Builder.Default
    private ApplicationStatus completed = ApplicationStatus.COMPLETED;
}
