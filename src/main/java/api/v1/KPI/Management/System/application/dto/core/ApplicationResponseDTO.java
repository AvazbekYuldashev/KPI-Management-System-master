package api.v1.KPI.Management.System.application.dto.core;

import api.v1.KPI.Management.System.application.enums.ApplicationStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApplicationResponseDTO {
    private String id;                       // ariza id
    private String title;                    // ariza nomi
    private String description;
    private ApplicationStatus status;        // ariza statusi
    private Float kpiBall;                   // KPI ball
    private Float kpiBallLimit;              // KPI ball limit
    private Long deadline;
    private Boolean visible;                 // ariza korinishi (delete soft)

    private String sendProfileId;            // yuboruvchi ID
    private String acceptorProfileId;        // qabul qilgan xodim id
    private String departmentId;             // yuborilgan bolim Id
    private String buildingId;               // yuborilgan bino manzil id
    private String offeringId;               // yuborilgan xizmat Id

    private LocalDateTime createdDate;            // foydalanuvchi yuborgan vaqt
    private LocalDateTime adminCheckedDate;       // admin tasdiqlagan vaqt
    private LocalDateTime employeeApprovedDate;   // xodim qabul qilgan vaqt
    private LocalDateTime employeeEndDate;        // yakunlangan vaqt
    private LocalDateTime limitDate;              // yakunlashga berilgan vaqt
    private LocalDateTime updatedDate;

}
