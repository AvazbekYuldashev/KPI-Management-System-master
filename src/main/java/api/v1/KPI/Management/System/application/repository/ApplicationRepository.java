package api.v1.KPI.Management.System.application.repository;

import api.v1.KPI.Management.System.application.controller.core.ApplicationStatusDTO;
import api.v1.KPI.Management.System.application.entity.ApplicationEntity;
import jakarta.transaction.Transactional;
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
}
