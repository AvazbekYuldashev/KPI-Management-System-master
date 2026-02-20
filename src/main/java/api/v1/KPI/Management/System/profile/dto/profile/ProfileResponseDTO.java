package api.v1.KPI.Management.System.profile.dto.profile;

import api.v1.KPI.Management.System.profile.enums.ProfileRole;
import api.v1.KPI.Management.System.security.enums.GeneralStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProfileResponseDTO {
    private String id;
    private String name;
    private String surname;
    private String username;
    private String photoId;
    private GeneralStatus status;
    private String buildingId;
    private String departmentId;

    private ProfileRole role;
    private LocalDateTime createdDate;
    private Boolean isEmployee;
}
