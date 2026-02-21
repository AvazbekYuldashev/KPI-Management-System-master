package api.v1.KPI.Management.System.department.service;

import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.department.entity.DepartmentEntity;
import api.v1.KPI.Management.System.department.repository.DepartmentRepository;
import api.v1.KPI.Management.System.exception.exps.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public DepartmentEntity create(DepartmentEntity entity){
        return departmentRepository.save(entity);
    }

    public DepartmentEntity findById(String id){return departmentRepository.findByIdAndVisibleTrue(id).get();}

    public DepartmentEntity findByTitle(String title){return departmentRepository.findByTitleAndVisibleTrue(title).get();}

    public Page<DepartmentEntity> findAllPage(Pageable pageable) {
        return departmentRepository.findAllPage(pageable);
    }

    public Boolean update(DepartmentEntity entity, AppLanguage lang){
        DepartmentEntity department = findById(entity.getId());
        if (department == null) throw new ResourceNotFoundException("Department Not Found");
        return departmentRepository.update(entity) > 0;
    }

}
