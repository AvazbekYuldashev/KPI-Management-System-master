package api.v1.KPI.Management.System.department.service;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.util.AppResponseUtil;
import api.v1.KPI.Management.System.department.entity.DepartmentEntity;
import api.v1.KPI.Management.System.department.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public DepartmentEntity create(DepartmentEntity entity){
        return departmentRepository.save(entity);
    }
    public DepartmentEntity findById(String id){
        Optional<DepartmentEntity> optional = departmentRepository.findById(id);
        if (optional.isEmpty()){
            throw new RuntimeException("Department with id " + id + " not found");
        }
        return optional.get();
    }
    public AppResponse<String> changeDetail(DepartmentEntity entity){
        int effectedRow = departmentRepository.changeDetail(entity.getId(), entity.getTitle(), entity.getDescription(), entity.getChief(), LocalDateTime.now());
        return AppResponseUtil.chek(effectedRow > 0);
    }

    public AppResponse<String> deleteById(String id) {
        int effectedRow = departmentRepository.deleteSoftById(id, false);
        return AppResponseUtil.chek(effectedRow > 0);
    }
}
