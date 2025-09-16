package api.v1.KPI.Management.System.application.service.user;

import api.v1.KPI.Management.System.application.dto.ApplicationResponseDTO;
import api.v1.KPI.Management.System.application.mapper.ApplicationMapper;
import api.v1.KPI.Management.System.application.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService extends ApplicationService {
    @Autowired
    private ApplicationMapper applicationMapper;


    public ApplicationResponseDTO userGetById(String id) {
        return applicationMapper.toResponseDTO(findById(id));
    }

    public PageImpl<ApplicationResponseDTO> userGetAll(int page, int size) {
        return getAll(page, size);
    }

}
