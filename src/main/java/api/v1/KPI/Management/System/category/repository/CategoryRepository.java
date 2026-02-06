package api.v1.KPI.Management.System.category.repository;

import api.v1.KPI.Management.System.category.entity.CategoryEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {

    // Id bo‘yicha va visible = true
    @Query("SELECT c FROM CategoryEntity c WHERE c.id = :id AND c.visible = true")
    Optional<CategoryEntity> findByIdAndVisibleTrue(String id);

    // Title bo‘yicha va visible = true
    @Query("SELECT c FROM CategoryEntity c WHERE c.title = :title AND c.visible = true")
    Optional<CategoryEntity> findByTitleAndVisibleTrue(String title);

    // Update qilish (null qiymatlarni saqlash)
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("""
        UPDATE CategoryEntity c SET
            c.title = COALESCE(:#{#entity.title}, c.title),
            c.description = COALESCE(:#{#entity.description}, c.description),
            c.departmentId = COALESCE(:#{#entity.departmentId}, c.departmentId),
            c.buildingId = COALESCE(:#{#entity.buildingId}, c.buildingId), 
            c.visible = COALESCE(:#{#entity.visible}, c.visible),
            c.updatedDate = CURRENT_TIMESTAMP
        WHERE c.id = :#{#entity.id}
    """)
    int update(@Param("entity") CategoryEntity entity);

    // Department bo‘yicha pagination
    @Query("SELECT c FROM CategoryEntity c WHERE c.department.id = :departmentId AND c.visible = true")
    Page<CategoryEntity> findAllByDepartmentId(@Param("departmentId") String departmentId, Pageable pageable);

    // Building bo‘yicha pagination
    @Query("SELECT c FROM CategoryEntity c WHERE c.building.id = :buildingId AND c.visible = true")
    Page<CategoryEntity> findAllByBuildingId(@Param("buildingId") String buildingId, Pageable pageable);

}
