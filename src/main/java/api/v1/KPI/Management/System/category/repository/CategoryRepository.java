package api.v1.KPI.Management.System.category.repository;

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
public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {

    @Query("SELECT c FROM CategoryEntity c WHERE c.visible = true")
    Page<CategoryEntity> findAllPage(PageRequest of);

    @Modifying
    @Transactional
    @Query("""
        UPDATE BuildingEntity d
            SET d.title = COALESCE(:title, d.title),
                d.description = COALESCE(:description, d.description),
                d.departmentId = COALESCE(:departmentId, d.departmentId),
                d.updatedDate = COALESCE(:updated_date, d.updatedDate)
        WHERE d.id = :id
    """)
    int changeDetail(@Param("id") String id,
                     @Param("title") String title,
                     @Param("description") String description,
                     @Param("departmentId") String departmentId,
                     @Param("updated_date") LocalDateTime now);

    @Modifying
    @Transactional
    @Query("UPDATE CategoryEntity SET visible = :visible WHERE id = :id")
    int deleteSoftById(String id, boolean b);

    @Query("SELECT c FROM CategoryEntity c WHERE c.id = :id AND c.visible = true")
    Optional<CategoryEntity> findByIdAndVisibleTrue(@Param("id") String id);

    @Query("SELECT c FROM CategoryEntity c WHERE c.departmentId = :id AND c.visible = true")
    Page<CategoryEntity> findByDepartmentIdPage(@Param("id") String id, PageRequest of);
}
