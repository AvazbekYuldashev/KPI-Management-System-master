package api.v1.KPI.Management.System.department.service;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.util.AppResponseUtil;
import api.v1.KPI.Management.System.department.dto.core.DepartmentResponseDTO;
import api.v1.KPI.Management.System.department.entity.DepartmentEntity;
import api.v1.KPI.Management.System.department.mapper.DepartmentMapper;
import api.v1.KPI.Management.System.department.repository.DepartmentRepository;
import api.v1.KPI.Management.System.exception.exps.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private DepartmentMapper departmentMapper;

    public DepartmentEntity create(DepartmentEntity entity){
        return departmentRepository.save(entity);
    }
    public DepartmentEntity findById(String id){
        Optional<DepartmentEntity> optional = departmentRepository.findByIdAndVisibleTrue(id);
        if (optional.isEmpty()){
            throw new ResourceNotFoundException("Department with id " + id + " not found");
        }
        return optional.get();
    }

    public Page<DepartmentResponseDTO> findAll(int page, Integer size){
        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<DepartmentEntity> pageObj = departmentRepository.findAllPage(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        List<DepartmentResponseDTO> response = pageObj.getContent().stream().map(departmentMapper::toResponseDTO).collect(Collectors.toList());
        long total = pageObj.getTotalElements();
        return new PageImpl<>(response, pageable, total);
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
