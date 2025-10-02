package api.v1.KPI.Management.System.profile.service.admin;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.exception.exps.ProfileStatusException;
import api.v1.KPI.Management.System.profile.dto.owner.*;
import api.v1.KPI.Management.System.profile.dto.profile.ProfileResponseDTO;
import api.v1.KPI.Management.System.profile.entity.ProfileEntity;
import api.v1.KPI.Management.System.profile.enums.ProfileRole;
import api.v1.KPI.Management.System.profile.mapper.ProfileMapper;
import api.v1.KPI.Management.System.profile.service.profile.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
public class ProfileAdminService extends ProfileService {

}
