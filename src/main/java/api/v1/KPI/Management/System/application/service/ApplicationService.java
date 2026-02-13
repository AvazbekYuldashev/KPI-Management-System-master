package api.v1.KPI.Management.System.application.service;

import api.v1.KPI.Management.System.application.entity.ApplicationEntity;
import api.v1.KPI.Management.System.application.repository.ApplicationRepository;
import api.v1.KPI.Management.System.exception.exps.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;

    public ApplicationEntity save(ApplicationEntity entity) {
        return applicationRepository.save(entity);
    }

    public ApplicationEntity findById(String id) {
        Optional<ApplicationEntity> optional = applicationRepository.findById(id);
        if (optional.isEmpty()){
            throw new ResourceNotFoundException("Application with id: " + id + " not found");
        }
        return optional.get();
    }
}
