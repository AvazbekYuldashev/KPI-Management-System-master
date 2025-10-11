package api.v1.KPI.Management.System.department.repository;

import api.v1.KPI.Management.System.department.entity.DepartmentEntity;
import api.v1.KPI.Management.System.profile.entity.ProfileEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, String> {

    @Modifying
    @Query("""
        UPDATE DepartmentEntity d
        SET d.title = COALESCE(:title, d.title),
            d.description = COALESCE(:description, d.description),
            d.chiefId = COALESCE(:chiefId, d.chiefId),
            d.updatedDate = COALESCE(:now, d.updatedDate)
        WHERE d.id = :id
    """)
    int changeDetail(
            @Param("id") String id,
            @Param("title") String title,
            @Param("description") String description,
            @Param("chief") ProfileEntity chief,
            @Param("now") LocalDateTime now
    );

    @Modifying
    @Transactional
    @Query("UPDATE DepartmentEntity SET visible = :visible WHERE id = :id")
    int deleteSoftById(@Param("id") String id, @Param("visible") boolean b);

    @Query("SELECT d FROM DepartmentEntity d WHERE d.id = :id AND d.visible = true")
    Optional<DepartmentEntity> findByIdAndVisibleTrue(String id);

    @Query("SELECT d FROM DepartmentEntity d WHERE d.visible = true ORDER BY d.createdDate DESC")
    Page<DepartmentEntity> findAllPage(PageRequest of);
}
