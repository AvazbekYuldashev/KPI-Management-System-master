package api.v1.KPI.Management.System.category.controller.admin;

import api.v1.KPI.Management.System.category.service.admin.CategoryAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/category")
@PreAuthorize("hasRole('ADMIN')")
public class CategoryAdminController {
    @Autowired
    private CategoryAdminService categoryAdminService;



}
