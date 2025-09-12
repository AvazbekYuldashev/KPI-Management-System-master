package api.v1.KPI.Management.System.department.entity;

import api.v1.KPI.Management.System.profile.entity.ProfileEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

/**
 * Entity class that represents a department in the system.
 * <p>
 * Stores information about the department such as title, description,
 * head of department (chief), visibility status, and audit details
 * (created/updated timestamps).
 * <p>
 * A department is associated with a {@link ProfileEntity} that
 * represents its chief.
 */
@Getter
@Setter
@Entity
@Table(name = "department")
public class DepartmentEntity {

    /**
     * Unique identifier of the department (UUID)
     */
    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    /**
     * Name/title of the department
     */
    @Column(name = "title")
    private String title;

    /**
     * Description of the department
     */
    @Column(name = "description")
    private String description;

    /**
     * ID of the department head (chief)
     */
    @Column(name = "profile_id")
    private String chiefId;

    /**
     * Profile entity representing the department head
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private ProfileEntity chief;

    /**
     * Visibility status of the department
     */
    @Column(name = "visible")
    private Boolean visible;

    /**
     * Date and time when the department was created
     */
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    /**
     * Date and time when the department was last updated
     */
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
}
