package api.v1.KPI.Management.System.application.dto.core;

import api.v1.KPI.Management.System.application.enums.ApplicationStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApplicationResponseDTO {

    private String id;
    private String title;
    private String description;
    private ApplicationStatus status;
    private Float kpiBall;
    private Float kpiBallLimit;
    private Long deadline;
    private Boolean visible;

    private String sendProfileId;
    private String sendProfileFullName;

    private String acceptorProfileId;
    private String acceptorProfileFullName;

    private String departmentId;
    private String departmentTitle;

    private String buildingId;
    private String buildingTitle;

    private String categoryId;
    private String categoryTitle;

    private String offeringId;
    private String offeringTitle;

    private LocalDateTime createdDate;
    private LocalDateTime adminCheckedDate;
    private LocalDateTime employeeApprovedDate;
    private LocalDateTime employeeEndDate;
    private LocalDateTime limitDate;
    private LocalDateTime updatedDate;

    public ApplicationResponseDTO(){
    }
    // --- Full constructor for JPA ---
    public ApplicationResponseDTO(String id, String title, String description, ApplicationStatus status,
                                  Float kpiBall, Float kpiBallLimit, Long deadline, Boolean visible,
                                  String sendProfileId, String sendProfileFullName,
                                  String acceptorProfileId, String acceptorProfileFullName,
                                  String departmentId, String departmentTitle,
                                  String buildingId, String buildingTitle,
                                  String categoryId, String categoryTitle,
                                  String offeringId, String offeringTitle,
                                  LocalDateTime createdDate, LocalDateTime adminCheckedDate,
                                  LocalDateTime employeeApprovedDate, LocalDateTime employeeEndDate,
                                  LocalDateTime limitDate, LocalDateTime updatedDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.kpiBall = kpiBall;
        this.kpiBallLimit = kpiBallLimit;
        this.deadline = deadline;
        this.visible = visible;

        this.sendProfileId = sendProfileId;
        this.sendProfileFullName = sendProfileFullName;
        this.acceptorProfileId = acceptorProfileId;
        this.acceptorProfileFullName = acceptorProfileFullName;

        this.departmentId = departmentId;
        this.departmentTitle = departmentTitle;
        this.buildingId = buildingId;
        this.buildingTitle = buildingTitle;

        this.categoryId = categoryId;
        this.categoryTitle = categoryTitle;
        this.offeringId = offeringId;
        this.offeringTitle = offeringTitle;

        this.createdDate = createdDate;
        this.adminCheckedDate = adminCheckedDate;
        this.employeeApprovedDate = employeeApprovedDate;
        this.employeeEndDate = employeeEndDate;
        this.limitDate = limitDate;
        this.updatedDate = updatedDate;
    }
}
