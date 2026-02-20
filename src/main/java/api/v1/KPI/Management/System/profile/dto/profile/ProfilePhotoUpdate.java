package api.v1.KPI.Management.System.profile.dto.profile;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfilePhotoUpdate {
    @NotBlank(message = "Profile Id required")
    private String id;
    @NotBlank(message = "Photo Id required")
    private String photoId;
}
