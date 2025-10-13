package api.v1.KPI.Management.System.offering.repository;

import api.v1.KPI.Management.System.offering.entity.OfferingEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface OfferingRepository extends JpaRepository<OfferingEntity, String> {
    @Query("SELECT o FROM OfferingEntity o WHERE o.id = :id AND o.visible = true ")
    Optional<OfferingEntity> findByIdAndVisibleTrue(String id);

    @Query("SELECT o FROM OfferingEntity o WHERE o.visible = true ORDER BY o.createdDate DESC")
    Page<OfferingEntity> findAllPage(PageRequest of);

    @Modifying
    @Transactional
    @Query("UPDATE OfferingEntity o SET o.visible = :visible WHERE o.id = :id")
    int deleteSoftById(String id, boolean b);

    @Modifying
    @Transactional
    @Query("""
        UPDATE OfferingEntity o
            SET o.title = COALESCE(:title, o.title),
                o.description = COALESCE(:description, o.description),
                o.kpiBall = COALESCE(:kpiBall, o.kpiBall),
                o.categoryId = COALESCE(:categoryId, o.categoryId),
                o.updatedDate = :now
            WHERE o.id = :id """)
    int changeDetail(String id, String title, String description, Float kpiBall, String categoryId, LocalDateTime now);

    @Query("SELECT o FROM OfferingEntity o WHERE o.categoryId = :id AND o.visible = true")
    Page<OfferingEntity> findByCategoryIdPage(String id, PageRequest of);
}
