package api.v1.KPI.Management.System.application.repository;

import api.v1.KPI.Management.System.application.dto.core.ApplicationResponseDTO;
import api.v1.KPI.Management.System.application.dto.core.ApplicationStatusDTO;
import api.v1.KPI.Management.System.application.entity.ApplicationEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationEntity, String> {

    @Modifying
    @Transactional
    @Query("UPDATE ApplicationEntity a SET a.status = :#{#dto.status} WHERE a.id = :#{#dto.id}")
    int changeStatus(ApplicationStatusDTO dto);

    @Query("SELECT a FROM ApplicationEntity a WHERE a.sendProfileId = :userId AND a.visible = TRUE ORDER BY a.createdDate DESC ")
    Page<ApplicationEntity> findAllByMyIdAndVisibleTruePage(String userId, Pageable pageable);
}
