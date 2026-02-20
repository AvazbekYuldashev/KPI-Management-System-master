package api.v1.KPI.Management.System.employee.service;

import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.exception.exps.ResourceNotFoundException;
import api.v1.KPI.Management.System.profile.entity.ProfileEntity;
import api.v1.KPI.Management.System.profile.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private ProfileRepository profileRepository;


    public ProfileEntity findById(String id, AppLanguage lang) {
        Optional<ProfileEntity> profileEntity = profileRepository.findById(id);
        if (profileEntity.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Profile with id %s not found", id));
        }
        return profileEntity.get();
    }


    public Page<ProfileEntity> findAllPageAndVisibleTrue(String id, Pageable pageable) {
        return profileRepository.findAllByDepartmentIdAndVisibleTrue(id, pageable);
    }


}
