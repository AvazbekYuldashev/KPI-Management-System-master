package api.v1.KPI.Management.System.department.service;

import api.v1.KPI.Management.System.department.dto.admin.DepartmentAdminCreateDTO;
import api.v1.KPI.Management.System.department.entity.DepartmentEntity;
import api.v1.KPI.Management.System.department.repository.DepartmentRepository;
import api.v1.KPI.Management.System.exception.exps.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public DepartmentEntity create(DepartmentEntity entity) {
        return departmentRepository.save(entity);
    }

    public DepartmentEntity findById(String id) {
        Optional<DepartmentEntity> optional = departmentRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("Department with id " + id + " not found");
        }
        return optional.get();
    }


}
