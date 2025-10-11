package api.v1.KPI.Management.System.department.repository;

import api.v1.KPI.Management.System.department.entity.DepartmentEntity;
import api.v1.KPI.Management.System.profile.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, String> {

    int changeDetail(String id, String title, String description, ProfileEntity chief, LocalDateTime now);

    int deleteSoftById(String id, boolean b);
}
