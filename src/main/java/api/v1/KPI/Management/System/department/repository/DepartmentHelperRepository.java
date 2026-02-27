package api.v1.KPI.Management.System.department.repository;

import api.v1.KPI.Management.System.department.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentHelperRepository extends JpaRepository<DepartmentEntity, String> {
}
