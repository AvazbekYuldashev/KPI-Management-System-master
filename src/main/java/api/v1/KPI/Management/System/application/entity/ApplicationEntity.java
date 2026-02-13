package api.v1.KPI.Management.System.application.entity;

import api.v1.KPI.Management.System.application.enums.ApplicationStatus;
import api.v1.KPI.Management.System.building.entity.BuildingEntity;
import api.v1.KPI.Management.System.department.entity.DepartmentEntity;
import api.v1.KPI.Management.System.offering.entity.OfferingEntity;
import api.v1.KPI.Management.System.profile.entity.ProfileEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "application")
public class ApplicationEntity {
    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;                       // ariza id
    @Column(name = "title", length = 150)
    private String title;                    // ariza nomi
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;              // xat mazmuni
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;        // ariza statusi
    @Column(name = "kpi_ball")
    private Float kpiBall;                   // KPI ball
    @Column(name = "kpi_ball_limit")
    private Float kpiBallLimit;              // KPI ball limit
    @Column(name = "deadline")
    private Long deadline;
    @Column(name = "visible")
    private Boolean visible;                 // ariza korinishi (delete soft)


    @Column(name = "send_profile_id")
    private String sendProfileId;            // yuboruvchi ID
    @Column(name = "acceptor_profile_id")
    private String acceptorProfileId;        // qabul qilgan xodim id
    @Column(name = "department_id")
    private String departmentId;             // yuborilgan bolim Id
    @Column(name = "building_id")
    private String buildingId;               // yuborilgan bino manzil id
    @Column(name = "offering_id")
    private String offeringId;               // yuborilgan xizmat Id



    @Column(name = "created_date")
    private LocalDateTime createdDate;            // foydalanuvchi yuborgan vaqt
    @Column(name = "admin_checked_date")
    private LocalDateTime adminCheckedDate;       // admin tasdiqlagan vaqt
    @Column(name = "employee_approved_date")
    private LocalDateTime employeeApprovedDate;   // xodim qabul qilgan vaqt
    @Column(name = "employee_end_date")
    private LocalDateTime employeeEndDate;        // yakunlangan vaqt
    @Column(name = "limit_date")
    private LocalDateTime limitDate;              // yakunlashga berilgan vaqt
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "send_profile_id", insertable = false, updatable = false)
    private ProfileEntity sendProfile;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "acceptor_profile_id", insertable = false, updatable = false)
    private ProfileEntity acceptorProfile;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", insertable = false, updatable = false)
    private DepartmentEntity department;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id", insertable = false, updatable = false)
    private BuildingEntity building;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offering_id", insertable = false, updatable = false)
    private OfferingEntity offering;

    @PrePersist                             //  default holat
    public void onCreate() {
        this.createdDate = LocalDateTime.now();
        this.visible = true;
        this.status = ApplicationStatus.SENT;
    }

}
