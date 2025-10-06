package api.v1.KPI.Management.System.application.service.admin;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.application.dto.ApplicationResponseDTO;
import api.v1.KPI.Management.System.application.dto.admin.ApplicationAdminCreateDTO;
import api.v1.KPI.Management.System.application.dto.admin.ApplicationAdminUpdateDTO;
import api.v1.KPI.Management.System.application.mapper.ApplicationMapper;
import api.v1.KPI.Management.System.application.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

@Service
public class ApplicationAdminService extends ApplicationService {
    @Autowired
    private ApplicationMapper applicationMapper;

    public ApplicationResponseDTO adminCreate(ApplicationAdminCreateDTO dto) {
        return applicationMapper.toResponseDTO(create(applicationMapper.toAdminCreateEntity(dto)));
    }

    public ApplicationResponseDTO adminGetById(String id) {
        return applicationMapper.toResponseDTO(findById(id));
    }

    public PageImpl<ApplicationResponseDTO> adminGetAll(int page, int size) {
        return getAll(page, size);
    }

    public AppResponse<String> adminDelete(String id) {
        return deleteById(id);
    }

    public AppResponse<String> adminUpdate(String id, ApplicationAdminUpdateDTO dto) {
        return updateDetail(id, applicationMapper.adminUpdateToDTO(dto));
    }


}
