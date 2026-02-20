package api.v1.KPI.Management.System.offering.entity;

import api.v1.KPI.Management.System.building.entity.BuildingEntity;
import api.v1.KPI.Management.System.category.entity.CategoryEntity;
import api.v1.KPI.Management.System.department.entity.DepartmentEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "offering")
public class OfferingEntity {
    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "kpi_ball")
    private Float kpiBall;

    @Column(name = "deadline")
    private Long deadline;

    @Column(name = "category_id")
    private String categoryId;

    @Column(name = "department_id")
    private String departmentId;

    @Column(name = "building_id")
    private String buildingId;

    @Column(name = "visible")
    private Boolean visible;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private CategoryEntity category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", insertable = false, updatable = false)
    private DepartmentEntity department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id", insertable = false, updatable = false)
    private BuildingEntity building;




    @PrePersist
    protected void onCreate() {
        this.visible = Boolean.TRUE;
        this.createdDate = LocalDateTime.now();
        this.updatedDate  = LocalDateTime.now();
        this.kpiBall = 0.0f;
    }
}
