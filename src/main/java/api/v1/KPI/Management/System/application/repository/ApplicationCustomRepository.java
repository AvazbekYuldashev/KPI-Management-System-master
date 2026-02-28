package api.v1.KPI.Management.System.application.repository;

import api.v1.KPI.Management.System.application.dto.core.ApplicationResponseDTO;
import api.v1.KPI.Management.System.application.dto.manager.ApplicationFilterDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ApplicationCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public PageImpl<ApplicationResponseDTO> filter(ApplicationFilterDTO dto, int page, int size) {

        Map<String, Object> params = new HashMap<>();
        StringBuilder where = new StringBuilder(" WHERE 1=1 ");

        // --- Dynamic filters ---
        if (dto.getId() != null) {
            where.append(" AND a.id = :id");
            params.put("id", dto.getId());
        }
        if (dto.getTitle() != null && !dto.getTitle().isBlank()) {
            where.append(" AND lower(a.title) LIKE :title");
            params.put("title", "%" + dto.getTitle().toLowerCase() + "%");
        }
        if (dto.getDescription() != null && !dto.getDescription().isBlank()) {
            where.append(" AND lower(a.description) LIKE :description");
            params.put("description", "%" + dto.getDescription().toLowerCase() + "%");
        }
        if (dto.getStatus() != null) {
            where.append(" AND a.status = :status");
            params.put("status", dto.getStatus());
        }
        if (dto.getKpiBall() != null) {
            where.append(" AND a.kpiBall = :kpiBall");
            params.put("kpiBall", dto.getKpiBall());
        }
        if (dto.getKpiBallLimit() != null) {
            where.append(" AND a.kpiBallLimit = :kpiBallLimit");
            params.put("kpiBallLimit", dto.getKpiBallLimit());
        }
        if (dto.getDeadline() != null) {
            where.append(" AND a.deadline = :deadline");
            params.put("deadline", dto.getDeadline());
        }
        if (dto.getVisible() != null) {
            where.append(" AND a.visible = :visible");
            params.put("visible", dto.getVisible());
        }
        if (dto.getSendProfileId() != null) {
            where.append(" AND a.sendProfileId = :sendProfileId");
            params.put("sendProfileId", dto.getSendProfileId());
        }
        if (dto.getAcceptorProfileId() != null) {
            where.append(" AND a.acceptorProfileId = :acceptorProfileId");
            params.put("acceptorProfileId", dto.getAcceptorProfileId());
        }
        if (dto.getDepartmentId() != null) {
            where.append(" AND a.departmentId = :departmentId");
            params.put("departmentId", dto.getDepartmentId());
        }
        if (dto.getBuildingId() != null) {
            where.append(" AND a.buildingId = :buildingId");
            params.put("buildingId", dto.getBuildingId());
        }
        if (dto.getOfferingId() != null) {
            where.append(" AND a.offeringId = :offeringId");
            params.put("offeringId", dto.getOfferingId());
        }

        // --- Date filters helper ---
        applyDateFilter(where, params, "createdDate", dto.getCreatedDateFrom(), dto.getCreatedDateTo());
        applyDateFilter(where, params, "adminCheckedDate", dto.getAdminCheckedDateFrom(), dto.getAdminCheckedDateTo());
        applyDateFilter(where, params, "employeeApprovedDate", dto.getEmployeeApprovedDateFrom(), dto.getEmployeeApprovedDateTo());
        applyDateFilter(where, params, "employeeEndDate", dto.getEmployeeEndDateFrom(), dto.getEmployeeEndDateTo());
        applyDateFilter(where, params, "limitDate", dto.getLimitDateFrom(), dto.getLimitDateTo());
        applyDateFilter(where, params, "updatedDate", dto.getUpdatedDateFrom(), dto.getUpdatedDateTo());

        // --- Joins ---
        String joins = " LEFT JOIN ProfileEntity sp ON sp.id = a.sendProfileId" +
                " LEFT JOIN ProfileEntity ap ON ap.id = a.acceptorProfileId" +
                " LEFT JOIN DepartmentEntity d ON d.id = a.departmentId" +
                " LEFT JOIN BuildingEntity b ON b.id = a.buildingId" +
                " LEFT JOIN OfferingEntity o ON o.id = a.offeringId" +
                " LEFT JOIN CategoryEntity c ON c.id = o.categoryId";

        // --- Select DTO ---
        String selectQuery = "SELECT new api.v1.KPI.Management.System.application.dto.core.ApplicationResponseDTO(" +
                "a.id, a.title, a.description, a.comments, a.status, a.kpiBall, a.kpiBallLimit, a.deadline, a.visible," +
                "sp.id, CONCAT(sp.name,' ',sp.surname)," +
                "ap.id, CONCAT(ap.name,' ',ap.surname)," +
                "d.id, d.title," +
                "b.id, b.title," +
                "c.id, c.title," +
                "o.id, o.title," +
                "a.createdDate, a.adminCheckedDate, a.employeeApprovedDate, a.employeeEndDate, a.limitDate, a.updatedDate" +
                ") FROM ApplicationEntity a " + joins + where.toString() +
                " ORDER BY a.createdDate DESC";

        Query querySelect = entityManager.createQuery(selectQuery, ApplicationResponseDTO.class);
        params.forEach(querySelect::setParameter);
        querySelect.setFirstResult(page * size);
        querySelect.setMaxResults(size);
        List<ApplicationResponseDTO> content = querySelect.getResultList();

        // --- Count query ---
        String countQuery = "SELECT count(a) FROM ApplicationEntity a " + joins + where.toString();
        Query queryCount = entityManager.createQuery(countQuery);
        params.forEach(queryCount::setParameter);
        Long total = (Long) queryCount.getSingleResult();

        return new PageImpl<>(content, PageRequest.of(page, size), total);
    }

    // --- Helper method for date filters ---
    private void applyDateFilter(StringBuilder where, Map<String, Object> params,
                                 String field, LocalDate from, LocalDate to) {
        if (from != null && to != null) {
            where.append(" AND a.").append(field)
                    .append(" BETWEEN :").append(field).append("From AND :").append(field).append("To");
            params.put(field + "From", from.atStartOfDay());
            params.put(field + "To", to.atTime(LocalTime.MAX));
        } else if (from != null) {
            where.append(" AND a.").append(field).append(" >= :").append(field).append("From");
            params.put(field + "From", from.atStartOfDay());
        } else if (to != null) {
            where.append(" AND a.").append(field).append(" <= :").append(field).append("To");
            params.put(field + "To", to.atTime(LocalTime.MAX));
        }
    }

}
