package api.v1.KPI.Management.System.building.repository;

import api.v1.KPI.Management.System.building.entity.BuildingEntity;
import api.v1.KPI.Management.System.category.entity.CategoryEntity;
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
public interface BuildingRepository extends JpaRepository<BuildingEntity, String> {
    @Query("SELECT b FROM BuildingEntity b WHERE b.id = :idP AND b.visible = TRUE ")
    Optional<BuildingEntity> findById(@Param("idP") String id);

    @Query("SELECT b FROM BuildingEntity b WHERE b.visible = true ORDER BY b.createdDate DESC")
    Page<BuildingEntity> findAllPage(PageRequest of);

    @Modifying
    @Transactional
    @Query("UPDATE BuildingEntity b SET b.visible = FALSE WHERE b.id = :idP")
    int deleteSoft(@Param("idP") String id);

    @Modifying
    @Transactional
    @Query("UPDATE BuildingEntity b SET " +
            "b.title = COALESCE(:title, b.title), " +
            "b.description = COALESCE(:description, b.description), " +
            "b.chiefId = COALESCE(:chiefId, b.chiefId), " +
            "b.departmentId = COALESCE(:departmentId, b.departmentId), " +
            "b.updatedDate =  :now " +
            "WHERE b.id = :id")
    int updateDetail(@Param("id") String id,
                     @Param("title") String title,
                     @Param("description") String description,
                     @Param("chiefId") String chiefId,
                     @Param("departmentId") String departmentId,
                     @Param("now") LocalDateTime now);
}
