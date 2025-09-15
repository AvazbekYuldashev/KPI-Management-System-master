package api.v1.KPI.Management.System.offering.repository;

import api.v1.KPI.Management.System.department.entity.DepartmentEntity;
import api.v1.KPI.Management.System.offering.entity.OfferingEntity;
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
public interface OfferingRepository extends JpaRepository<OfferingEntity, String> {


    @Query("SELECT o FROM OfferingEntity o WHERE o.id = :idP AND o.visible = TRUE ")
    Optional<OfferingEntity> findById(@Param("idP") String id);

    @Query("SELECT o FROM OfferingEntity o WHERE o.visible = true ORDER BY o.createdDate DESC")
    Page<OfferingEntity> findAllPage(PageRequest of);

    @Modifying
    @Transactional
    @Query("UPDATE OfferingEntity o SET " +
            "o.title = COALESCE(:title, o.title), " +
            "o.description = COALESCE(:description, o.description), " +
            "o.categoryId = COALESCE(:categoryId, o.categoryId), " +
            "o.kpiBall = COALESCE(:kpiBall, o.kpiBall), " +
            "o.updatedDate =  :now " +
            "WHERE o.id = :id")
    int updateDetail(@Param("id") String id,
                     @Param("title") String title,
                     @Param("description") String description,
                     @Param("categoryId") String categoryId,
                     @Param("kpiBall")  Float kpiBall,
                     @Param("now") LocalDateTime now);

    @Modifying
    @Transactional
    @Query("UPDATE OfferingEntity o SET o.visible = false WHERE o.id = :id ")
    int deleteSoft(@Param("id") String id);
}
