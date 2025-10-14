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
    private String id;                       // xat id

    @Column(name = "title", length = 150)
    private String title;                    // xat nomi

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;              // xat mazmuni

    @Column(name = "send_profile_id")
    private String sendProfileId;            // yuboruvchi ID

    @Column(name = "offering_id")
    private String offeringId;               // yuborilgan xizmat Id

    @Column(name = "building_id")
    private String buildingId;               // yuborilgan bino manzil id

    @Column(name = "department_id")
    private String departmentId;             // yuborilgan bolim Id

    @Column(name = "acceptor_profile_id")
    private String acceptorProfileId;        // qabul qilgan xodim id

    @Column(name = "visible")
    private Boolean visible;                 // ariza korinishi (delete soft)

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;        // ariza statusi


    @Column(name = "created_date")
    private LocalDateTime createdDate;       // foydalanuvchi yuborgan vaqt
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
    @Column(name = "approved_date")
    private LocalDateTime approvedDate;      // admin tasdiqlagan vaqt
    @Column(name = "rejected_date")
    private LocalDateTime rejectedDate;      // admin rad etilgan vaqt
    @Column(name = "accepted_date")
    private LocalDateTime acceptedDate;      // xodim qabul qilgan vaqt
    @Column(name = "denied_date")
    private LocalDateTime deniedDate;        // xodim rad etilgan vaqt
    @Column(name = "completed_date")
    private LocalDateTime completedDate;     // yakunlangan vaqt

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "acceptor_profile_id", insertable = false, updatable = false)
    private ProfileEntity acceptorProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "send_profile_id", insertable = false, updatable = false)
    private ProfileEntity sendProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id", insertable = false, updatable = false)
    private BuildingEntity building;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", insertable = false, updatable = false)
    private DepartmentEntity department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offering_id", insertable = false, updatable = false)
    private OfferingEntity offering;

    @PrePersist
    public void onCreate() {
        this.createdDate = LocalDateTime.now();
        this.visible = true;
        this.status = ApplicationStatus.SENT; // yoki default holat
    }

}
