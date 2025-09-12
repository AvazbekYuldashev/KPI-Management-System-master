package api.v1.KPI.Management.System.category.repository;

import api.v1.KPI.Management.System.category.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {


    int deleteSoft(String id);

    int updateDetail(String id, String title, String description, LocalDateTime now);

    Page<CategoryEntity> findAllPage(PageRequest of);
}
