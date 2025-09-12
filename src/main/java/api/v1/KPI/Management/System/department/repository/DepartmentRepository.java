package api.v1.KPI.Management.System.department.repository;

import api.v1.KPI.Management.System.department.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, String> {

    @Query("SELECT d FROM DepartmentEntity d WHERE d.id = :idP AND d.visible = TRUE ")
    Optional<DepartmentEntity> findById(@Param("idP") String id);

}
