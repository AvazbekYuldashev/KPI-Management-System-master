package api.v1.KPI.Management.System.application.entity;

import api.v1.KPI.Management.System.application.enums.ApplicationStatus;
import api.v1.KPI.Management.System.building.entity.BuildingEntity;
import api.v1.KPI.Management.System.category.entity.CategoryEntity;
import api.v1.KPI.Management.System.department.entity.DepartmentEntity;
import api.v1.KPI.Management.System.profile.entity.ProfileEntity;
import api.v1.KPI.Management.System.security.enums.GeneralStatus;
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
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "send_profile_id")
    private String sendProfileId;

    @Column(name = "building_id")
    private String buildingId;

    @Column(name = "acceptor_profile_id")
    private String acceptorProfileId;



    @Column(name = "visible")
    private Boolean visible;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "acceptor_profile_id", insertable = false, updatable = false)
    private ProfileEntity acceptorProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "send_profile_id", insertable = false, updatable = false)
    private ProfileEntity sendProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id", insertable = false, updatable = false)
    private BuildingEntity building;


}
