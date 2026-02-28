package api.v1.KPI.Management.System.application.repository;

import api.v1.KPI.Management.System.application.dto.core.ApplicationGetterDTO;
import api.v1.KPI.Management.System.application.dto.core.ApplicationResponseDTO;
import api.v1.KPI.Management.System.application.dto.core.ApplicationStatusDTO;
import api.v1.KPI.Management.System.application.entity.ApplicationEntity;
import api.v1.KPI.Management.System.kpi.dto.core.KpiResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationEntity, String> {
    @Modifying
    @Transactional
    @Query("UPDATE ApplicationEntity a SET a.status = :#{#dto.status} WHERE a.id = :#{#dto.id}")
    int changeStatus(ApplicationStatusDTO dto);

    @Query("SELECT a FROM ApplicationEntity a WHERE a.sendProfileId = :userId AND a.visible = TRUE ORDER BY a.createdDate DESC ")
    Page<ApplicationEntity> findAllByMyIdAndVisibleTruePage(String userId, Pageable pageable);
    @Query("""
        SELECT new api.v1.KPI.Management.System.kpi.dto.core.KpiResponseDTO(
            a.acceptorProfileId,
            p.name,
            p.surname,
            SUM(CASE WHEN a.status = :#{#dto.accepted} THEN 1 ELSE 0 END), 
            SUM(CASE WHEN a.status = :#{#dto.rejected} THEN 1 ELSE 0 END), 
            SUM(CASE WHEN a.status = :#{#dto.completed} THEN 1 ELSE 0 END), 
            COALESCE(SUM( 
                CASE WHEN a.status = :#{#dto.completed} THEN a.kpiBall ELSE 0 END 
            ),0) 
        ) 
        FROM ApplicationEntity a 
        JOIN a.acceptorProfile p 
        WHERE (:#{#dto.acceptorProfileId} IS NULL OR a.acceptorProfileId = :#{#dto.acceptorProfileId}) 
        AND a.visible = true 
        GROUP BY a.acceptorProfileId, p.name, p.surname
        ORDER BY MAX(a.createdDate) DESC 
        """)
    Page<KpiResponseDTO> getEmployeeKpi(@Param("dto") ApplicationGetterDTO dto, Pageable pageable);

    @Query("SELECT a FROM ApplicationEntity a WHERE a.buildingId = :id AND a.visible = TRUE ")
    Page<ApplicationEntity> findAllByBuildingIdAndVisibleTruePage(String id, Pageable pageable);
}
