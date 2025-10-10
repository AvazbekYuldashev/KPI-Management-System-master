package api.v1.KPI.Management.System.offering.controller.owner;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.offering.dto.OfferingResponseDTO;
import api.v1.KPI.Management.System.offering.dto.admin.OfferingAdminCreateDTO;
import api.v1.KPI.Management.System.offering.dto.admin.OfferingAdminUpdateDTO;
import api.v1.KPI.Management.System.offering.service.owner.OfferingOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owner/offering")
@PreAuthorize("hasRole('OWNER')")
public class OfferingOwnerController {
    @Autowired
    private OfferingOwnerService offeringOwnerService;

    public static int getCurrentPage(Integer page) {
        return page > 0 ? page - 1 : 1;
    }
}
