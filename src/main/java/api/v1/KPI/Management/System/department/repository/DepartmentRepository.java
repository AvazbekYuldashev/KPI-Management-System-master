package api.v1.KPI.Management.System.department.repository;

import api.v1.KPI.Management.System.department.entity.DepartmentEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, String> {

    @Query("SELECT d FROM DepartmentEntity d WHERE d.id = :idP AND d.visible = TRUE ")
    Optional<DepartmentEntity> findById(@Param("idP") String id);

    @Query("SELECT d FROM DepartmentEntity d WHERE d.visible = true ORDER BY d.createdDate DESC")
    Page<DepartmentEntity> findAllPage(PageRequest of);

    @Modifying
    @Transactional
    @Query("UPDATE DepartmentEntity SET visible = FALSE WHERE id = :idP")
    int deleteSoft(DepartmentEntity entity);
}
