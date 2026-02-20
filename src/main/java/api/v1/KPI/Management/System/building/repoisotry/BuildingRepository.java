package api.v1.KPI.Management.System.building.repoisotry;

import api.v1.KPI.Management.System.building.entity.BuildingEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BuildingRepository extends JpaRepository<BuildingEntity, String> {
    @Query("SELECT b FROM BuildingEntity b WHERE b.id = :id AND b.visible =TRUE")
    Optional<BuildingEntity> findByIdAndVisibleTrue(@Param("id") String id);

    @Query("SELECT b FROM BuildingEntity b WHERE b.title = :title AND b.visible = TRUE")
    Optional<BuildingEntity> findByTitleAndVisibleTrue(@Param("title") String title);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("""
        UPDATE BuildingEntity b SET
            b.title = COALESCE(:#{#entity.title}, b.title),
            b.description = COALESCE(:#{#entity.description}, b.description),
            b.chiefId = COALESCE(:#{#entity.chiefId}, b.chiefId),
            b.departmentId = COALESCE(:#{#entity.departmentId}, b.departmentId),
            b.updatedDate = CURRENT_TIMESTAMP
        WHERE b.id = :#{#entity.id}
    """)
    int update(@Param("entity") BuildingEntity entity);


    @Query("SELECT b FROM BuildingEntity b WHERE b.visible = TRUE ORDER BY  b.createdDate DESC")
    Page<BuildingEntity> findAllPageAndVisibleTrue(Pageable pageable);

    @Query("SELECT b FROM BuildingEntity b ORDER BY b.createdDate DESC ")
    Page<BuildingEntity> findAllPage(Pageable pageable);

    @Query("SELECT b FROM BuildingEntity b WHERE b.departmentId =:id AND b.visible = TRUE ORDER BY b.createdDate DESC ")
    Page<BuildingEntity> findAllPageByDepartmentId(@Param("id") String id, Pageable pageable);
}
