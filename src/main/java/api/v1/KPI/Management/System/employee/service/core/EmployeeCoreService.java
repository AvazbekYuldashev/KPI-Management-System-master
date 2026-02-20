package api.v1.KPI.Management.System.employee.service.core;

import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.employee.dto.EmployeeResponseDTO;
import api.v1.KPI.Management.System.employee.mapper.EmployeeMapper;
import api.v1.KPI.Management.System.employee.service.EmployeeService;
import api.v1.KPI.Management.System.profile.entity.ProfileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeCoreService extends EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    public EmployeeResponseDTO getById(String id, AppLanguage lang){
        return employeeMapper.toResponseDTO(findById(id, lang));
    }

    public Page<EmployeeResponseDTO> findByDepartmentId(String id, Integer page, Integer size, AppLanguage lang) {
        Pageable pageable = PageRequest.of(page, size);

        // Bazadan sahifa bo‘yicha ma'lumotlarni olish
        Page<ProfileEntity> entitiesPage = findAllPageAndVisibleTrue(id, pageable);

        // Entity → DTO map qilish
        List<EmployeeResponseDTO> dtoList = entitiesPage.getContent().stream()
                .map(entity -> {return employeeMapper.toResponseDTO(entity);}).toList();

        // PageImpl orqali sahifa va pagination ma’lumotlarini saqlab DTO qaytarish
        return new PageImpl<>(dtoList, pageable, entitiesPage.getTotalElements());
    }

}
