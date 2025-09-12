package api.v1.KPI.Management.System.department.service.admin;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.department.dto.DepartmentResponseDTO;
import api.v1.KPI.Management.System.department.dto.admin.DepartmentAdminCreateDTO;
import api.v1.KPI.Management.System.department.dto.admin.DepartmentAdminUpdateDTO;
import api.v1.KPI.Management.System.department.entity.DepartmentEntity;
import api.v1.KPI.Management.System.department.mapper.DepartmentMapper;
import api.v1.KPI.Management.System.department.repository.DepartmentRepository;
import api.v1.KPI.Management.System.department.service.DepartmentService;
import api.v1.KPI.Management.System.profile.dto.ProfileResponseDTO;
import api.v1.KPI.Management.System.profile.entity.ProfileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentAdminService extends DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private DepartmentRepository departmentRepository;

    public DepartmentResponseDTO adminCreate(DepartmentAdminCreateDTO dto) {
        return departmentMapper.toResponseDTO(create(departmentMapper.toAdminCreateEntity(dto)));
    }

    public DepartmentResponseDTO getById(String id) {
        return departmentMapper.toResponseDTO(findById(id));
    }

    public PageImpl<DepartmentResponseDTO> getAll(int page, int size) {
        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<DepartmentEntity> pageObj = departmentRepository.findAllPage(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        List<DepartmentResponseDTO> response = pageObj.getContent().stream().map(departmentMapper::toResponseDTO).collect(Collectors.toList());
        long total = pageObj.getTotalElements();
        return new PageImpl<>(response, pageable, total);
    }

    public AppResponse<String> adminDelete(String id) {
        return deleteById(id);
    }


    public AppResponse<String> adminUpdate(String id, DepartmentAdminUpdateDTO dto) {
        return updateDetail(id, dto);
    }
}
