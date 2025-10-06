package api.v1.KPI.Management.System.department.service;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.department.dto.DepartmentResponseDTO;
import api.v1.KPI.Management.System.department.dto.admin.DepartmentAdminCreateDTO;
import api.v1.KPI.Management.System.department.dto.admin.DepartmentAdminUpdateDTO;
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

    public DepartmentEntity create(DepartmentEntity entity) {
        return departmentRepository.save(entity);
    }

    public DepartmentResponseDTO getById(String id){
        return departmentMapper.toResponseDTO(findById(id));
    }

    public DepartmentEntity findById(String id) {
        Optional<DepartmentEntity> optional = departmentRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("Department with id " + id + " not found");
        }
        return optional.get();
    }

    public AppResponse<String> deleteById(String id) {
        DepartmentEntity entity = findById(id);
        int effectedRow = departmentRepository.deleteSoft(entity);
        if (effectedRow > 0) {
            return new AppResponse<>("Department with id " + id + " deleted successfully");
        }
        return new AppResponse<>("Department with id " + id + " not found");
    }

    public AppResponse<String> updateDetail(String id, DepartmentAdminUpdateDTO dto) {
        findById(id);
        int EffectedRow = departmentRepository.updateDetail(id, dto.getTitle(), dto.getDescription(), dto.getChiefId(), LocalDateTime.now());
        if (EffectedRow > 0) {
            return new AppResponse<>("Department with id " + id + " updated successfully");
        }
        return new AppResponse<>("Department with id " + id + " not found");
    }

    public PageImpl<DepartmentResponseDTO> getAll(int page, int size) {
        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<DepartmentEntity> pageObj = departmentRepository.findAllPage(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        List<DepartmentResponseDTO> response = pageObj.getContent().stream().map(departmentMapper::toResponseDTO).collect(Collectors.toList());
        long total = pageObj.getTotalElements();
        return new PageImpl<>(response, pageable, total);
    }

}
