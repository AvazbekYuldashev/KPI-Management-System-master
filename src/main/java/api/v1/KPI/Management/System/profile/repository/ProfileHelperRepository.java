package api.v1.KPI.Management.System.profile.repository;

import api.v1.KPI.Management.System.profile.entity.ProfileEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ProfileHelperRepository extends JpaRepository<ProfileEntity, String> {

    @Query("SELECT p FROM ProfileEntity p WHERE p.id = :id AND p.visible = TRUE ")
    Optional<ProfileEntity> findByIdAndVisibleTrue(@Param("id") String id);

    @Modifying
    @Transactional
    @Query("""
       UPDATE ProfileEntity p
       SET p.isEmployee = :aTrue,
           p.departmentId = COALESCE(:departmentId, p.departmentId),
           p.updatedDate = :now
       WHERE p.id = :chiefId
       """)
    void changeDepartmentId(@Param("chiefId") String chiefId, @Param("departmentId") String departmentId, @Param("aTrue") Boolean aTrue, @Param("now") LocalDateTime now);

    @Modifying
    @Transactional
    @Query("""
        UPDATE ProfileEntity p 
        SET p.isEmployee = :aTrue,
            p.departmentId = COALESCE(:departmentId, p.departmentId),
            p.buildingId = COALESCE(:buildingId, p.buildingId),
            p.updatedDate = :now
        WHERE p.id = :id
        """)
    void changebuilding(@Param("id") String id, @Param("departmentId") String departmentId, @Param("buildingId") String buildingId, @Param("aTrue")Boolean aTrue, @Param("now") LocalDateTime now);

}
