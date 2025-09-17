package api.v1.KPI.Management.System.application.repository;

import api.v1.KPI.Management.System.application.entity.ApplicationEntity;
import api.v1.KPI.Management.System.application.enums.ApplicationStatus;
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
public interface ApplicationRepository extends JpaRepository<ApplicationEntity, String> {
    @Query("SELECT a FROM ApplicationEntity a WHERE a.id = :idP AND a.visible = TRUE ")
    Optional<ApplicationEntity> findById(@Param("idP") String id);

    @Query("SELECT a FROM ApplicationEntity a WHERE a.visible = true ORDER BY a.createdDate DESC")
    Page<ApplicationEntity> findAllPage(PageRequest of);
    @Query("SELECT a FROM ApplicationEntity a WHERE a.sendProfile = :profileId AND a.visible = true ORDER BY a.createdDate DESC")
    Page<ApplicationEntity> findAllByProfileId(PageRequest of, @Param("profileId") String profileId);

    @Modifying
    @Transactional
    @Query("UPDATE ApplicationEntity a SET " +
            "a.title = COALESCE(:title, a.title), " +
            "a.description = COALESCE(:description, a.description), " +
            "a.status = COALESCE(:status, a.status), " +
            "a.sendProfileId = COALESCE(:sendProfileId, a.sendProfileId), " +
            "a.acceptorProfileId = COALESCE(:acceptorProfileId, a.acceptorProfileId)," +
            "a.buildingId = COALESCE(:buildingId, a.buildingId), " +
            "a.updatedDate =  :now " +
            "WHERE a.id = :id")
    int updateDetail(@Param("id") String id,
                     @Param("title") String title,
                     @Param("description") String description,
                     @Param("status") ApplicationStatus status,
                     @Param("sendProfileId") String sendProfileId,
                     @Param("acceptorProfileId") String acceptorProfileId,
                     @Param("buildingId") String buildingId,
                     @Param("now") LocalDateTime now);

    @Modifying
    @Transactional
    @Query("UPDATE ApplicationEntity a SET a.visible = false WHERE a.id = :id ")
    int deleteSoft(@Param("id") String id);

}
