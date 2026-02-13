package api.v1.KPI.Management.System.application.dto.core;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ApplicationCreateDTO {
    private String title;                    // ariza nomi
    private String description;
    private String departmentId;             // yuborilgan bolim Id
    private String buildingId;               // yuborilgan bino manzil id
    private String offeringId;               // yuborilgan xizmat Id
}
