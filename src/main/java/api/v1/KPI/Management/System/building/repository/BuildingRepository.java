package api.v1.KPI.Management.System.building.repository;

import api.v1.KPI.Management.System.building.entity.BuildingEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface BuildingRepository extends JpaRepository<BuildingEntity, String> {

    @Modifying
    @Transactional
    @Query("""
        UPDATE BuildingEntity d
        SET d.title = COALESCE(:title, d.title),
            d.description = COALESCE(:description, d.description),
            d.chiefId = COALESCE(:chiefId, d.chiefId),
            d.departmentId = COALESCE(:departmentId, d.departmentId),
            d.updatedDate = COALESCE(:updated_date, d.updatedDate)
        WHERE d.id = :id
    """)
    int changeDetail(@Param("id") String id,
                     @Param("title") String title,
                     @Param("description") String description,
                     @Param("chiefId") String chiefId,
                     @Param("departmentId") String departmentId,
                     @Param("updated_date") LocalDateTime now);

    @Modifying
    @Transactional
    @Query("UPDATE BuildingEntity b SET b.visible = :visible WHERE b.id = :id")
    int deleteSoftById(@Param("id") String id, @Param("visible") boolean visible);

    @Query("SELECT b FROM BuildingEntity b WHERE b.id = :id AND b.visible = true")
    Optional<BuildingEntity> findByIdAndVisibleTrue(@Param("id") String id);

    @Query("SELECT b FROM BuildingEntity b WHERE b.visible = true ORDER BY b.createdDate DESC")
    Page<BuildingEntity> findAllPage(Pageable pageable);

    @Query("SELECT b FROM BuildingEntity b WHERE b.visible = true AND b.departmentId = :id ORDER BY b.createdDate DESC")
    Page<BuildingEntity> findByDepartmentIdPage(@Param("id") String id, Pageable pageable);
}
