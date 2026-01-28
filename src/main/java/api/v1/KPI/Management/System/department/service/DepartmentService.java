package api.v1.KPI.Management.System.department.service;

import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.department.entity.DepartmentEntity;
import api.v1.KPI.Management.System.department.repository.DepartmentRepository;
import api.v1.KPI.Management.System.exception.exps.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public DepartmentEntity create(DepartmentEntity entity){
        return departmentRepository.save(entity);
    }
    public DepartmentEntity findById(String id){
        Optional<DepartmentEntity> optional = departmentRepository.findByIdAndVisibleTrue(id);
        return optional.get();
    }
    public DepartmentEntity findByTitle(String title){
        Optional<DepartmentEntity> optional = departmentRepository.findByTitleAndVisibleTrue(title);
        return optional.get();
    }
    public Boolean update(DepartmentEntity entity, AppLanguage lang){
        DepartmentEntity department = findById(entity.getId());
        if (department == null) throw new ResourceNotFoundException("Department Not Found");
        return departmentRepository.update(entity) > 0;
    }
}
