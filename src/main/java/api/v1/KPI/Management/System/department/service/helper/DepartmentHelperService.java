package api.v1.KPI.Management.System.department.service.helper;

import api.v1.KPI.Management.System.department.entity.DepartmentEntity;
import api.v1.KPI.Management.System.department.repository.DepartmentHelperRepository;
import api.v1.KPI.Management.System.exception.exps.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentHelperService {
    @Autowired
    private DepartmentHelperRepository departmentHelperRepository;

    public DepartmentEntity findById(String id) {
        Optional<DepartmentEntity> optional = departmentHelperRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("Department with id: " + id + " not found");
        }
        return optional.get();

    }
}
