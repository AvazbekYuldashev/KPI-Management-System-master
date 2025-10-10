package api.v1.KPI.Management.System.offering.controller.admin;

import api.v1.KPI.Management.System.offering.service.admin.OfferingAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/offering")
@PreAuthorize("hasRole('ADMIN')")
public class OfferingAdminController {
    @Autowired
    private OfferingAdminService offeringAdminService;

    public static int getCurrentPage(Integer page) {
        return page > 0 ? page - 1 : 1;
    }

}
