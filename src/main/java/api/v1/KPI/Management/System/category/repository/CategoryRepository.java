package api.v1.KPI.Management.System.category.repository;

import api.v1.KPI.Management.System.category.entity.CategoryEntity;
import api.v1.KPI.Management.System.department.entity.DepartmentEntity;
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
    @Query("SELECT c FROM CategoryEntity c WHERE c.id = :idP AND c.visible = TRUE ")
    Optional<CategoryEntity> findById(@Param("idP") String id);

    @Query("SELECT c FROM CategoryEntity c WHERE c.visible = true ORDER BY c.createdDate DESC")
    Page<CategoryEntity> findAllPage(PageRequest of);

    @Modifying
    @Transactional
    @Query("UPDATE CategoryEntity c SET c.visible = FALSE WHERE c.id = :idP")
    int deleteSoft(@Param("idP") String id);

    @Modifying
    @Transactional
    @Query("UPDATE CategoryEntity c SET " +
            "c.title = COALESCE(:title, c.title), " +
            "c.description = COALESCE(:description, c.description), " +
            "c.departmentId = COALESCE(:departmentId, c.departmentId), " +
            "c.updatedDate =  :now " +
            "WHERE c.id = :id")
    int updateDetail(@Param("id") String id,
                     @Param("title") String title,
                     @Param("description") String description,
                     @Param("departmentId") String departmentId,
                     @Param("now") LocalDateTime now);
}

