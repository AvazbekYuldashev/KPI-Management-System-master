package api.v1.KPI.Management.System.profile.dto.admin;

import api.v1.KPI.Management.System.security.enums.GeneralStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileAdminChangeStatusDTO {
    private String profileId;
    private GeneralStatus status;
}
