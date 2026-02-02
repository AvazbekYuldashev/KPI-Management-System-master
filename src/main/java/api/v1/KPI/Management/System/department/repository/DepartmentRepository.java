package api.v1.KPI.Management.System.department.repository;

import api.v1.KPI.Management.System.department.entity.DepartmentEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, String> {
    @Query("SELECT d FROM DepartmentEntity d WHERE d.id = :id AND d.visible = TRUE")
    Optional<DepartmentEntity> findByIdAndVisibleTrue(@Param("id") String id);

    @Query("SELECT d FROM DepartmentEntity d WHERE d.title = :title AND d.visible = TRUE ")
    Optional<DepartmentEntity> findByTitleAndVisibleTrue(String title);

    @Modifying
    @Transactional
    @Query("""
        UPDATE DepartmentEntity d SET
            d.title = COALESCE(:#{#entity.title}, d.title),
            d.description = COALESCE(:#{#entity.description}, d.description),
            d.chiefId = COALESCE(:#{#entity.chiefId}, d.chiefId),
            d.visible = COALESCE(:#{#entity.visible}, d.visible),
            d.updatedDate = CURRENT_TIMESTAMP
        WHERE d.id = :#{#entity.id}
    """)
    int update(@Param("entity") DepartmentEntity entity);
}
