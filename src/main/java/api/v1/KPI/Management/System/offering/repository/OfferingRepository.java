package api.v1.KPI.Management.System.offering.repository;

import api.v1.KPI.Management.System.offering.entity.OfferingEntity;
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
public interface OfferingRepository extends JpaRepository<OfferingEntity, String> {

    @Query("SELECT o FROM OfferingEntity o WHERE o.id =:id AND o.visible = TRUE ")
    Optional<OfferingEntity> findByIdAndVisibleTrue(@Param("id") String id);

    @Query("SELECT o FROM OfferingEntity o WHERE o.title =:title AND o.visible = TRUE ")
    Optional<OfferingEntity> findByTitleAndVisibleTrue(@Param("title") String title);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("""
        UPDATE OfferingEntity o
        SET
            o.title = COALESCE(:#{#entity.title}, o.title),
            o.description = COALESCE(:#{#entity.description}, o.description),
            o.kpiBall = COALESCE(:#{#entity.kpiBall}, o.kpiBall),
            o.deadline = COALESCE(:#{#entity.deadline}, o.deadline),
            o.categoryId = COALESCE(:#{#entity.categoryId}, o.categoryId),
            o.departmentId = COALESCE(:#{#entity.departmentId}, o.departmentId),
            o.updatedDate = CURRENT_TIMESTAMP
        WHERE o.id = :#{#entity.id}
    """)
    int update(@Param("entity") OfferingEntity entity);

    @Query("SELECT o FROM OfferingEntity o WHERE o.visible = TRUE ")
    Page<OfferingEntity> findAllPageAndVisibleTrue(Pageable pageable);

    @Query("SELECT o FROM OfferingEntity o WHERE o.categoryId = :id AND o.visible = TRUE ")
    Page<OfferingEntity> findAllPageByCategoryId(@Param("id") String id, Pageable pageable);

    @Query("SELECT o FROM OfferingEntity o ORDER BY o.createdDate DESC ")
    Page<OfferingEntity> findAllPage(Pageable pageable);
}
