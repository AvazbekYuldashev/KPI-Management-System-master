package api.v1.KPI.Management.System.application.dto.manager;

import api.v1.KPI.Management.System.application.enums.ApplicationStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ApplicationFilterDTO {
    private String id;                       // ariza id
    private String title;                    // ariza nomi
    private String description;              // ariza mazmuni
    private ApplicationStatus status;        // ariza statusi
    private Float kpiBall;                   // KPI ball
    private Float kpiBallLimit;              // KPI ball limit
    private Long deadline;                   // Limit data
    private Boolean visible;                 // ariza korinishi (delete soft)

    private String sendProfileId;            // yuboruvchi ID
    private String acceptorProfileId;        // qabul qilgan xodim id
    private String departmentId;             // yuborilgan bolim Id
    private String buildingId;               // yuborilgan bino manzil id
    private String offeringId;               // yuborilgan xizmat Id

    private LocalDate createdDateFrom;            // foydalanuvchi yuborgan vaqt boshlanishi
    private LocalDate createdDateTo;              // foydalanuvchi yuborgan vaqt tugashi

    private LocalDate adminCheckedDateFrom;       // admin tasdiqlagan vaqt boshlanishi
    private LocalDate adminCheckedDateTo;         // admin tasdiqlagan vaqt tugashi

    private LocalDate employeeApprovedDateFrom;   // xodim qabul qilgan vaqt boshlanishi
    private LocalDate employeeApprovedDateTo;     // xodim qabul qilgan vaqt tugashi

    private LocalDate employeeEndDateFrom;        // yakunlangan vaqt boshlanishi
    private LocalDate employeeEndDateTo;          // yakunlangan vaqt tugashi

    private LocalDate limitDateFrom;              // yakunlashga berilgan vaqt boshlanishi
    private LocalDate limitDateTo;                // yakunlashga berilgan vaqt tugashi

    private LocalDate updatedDateFrom;            // yangilash sanasi boshlanishi
    private LocalDate updatedDateTo;              // yangilash sanasi tugashi

}
