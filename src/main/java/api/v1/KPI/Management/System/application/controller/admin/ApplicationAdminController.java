package api.v1.KPI.Management.System.application.controller.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/application")
@PreAuthorize("hasRole('ADMIN')")
public class ApplicationAdminController {
}
