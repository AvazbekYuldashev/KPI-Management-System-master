package api.v1.KPI.Management.System.profile.dto.owner;

import api.v1.KPI.Management.System.profile.enums.ProfileRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileOwnerChangeRoleDTO {
    private String id;
    private ProfileRole role;
}
