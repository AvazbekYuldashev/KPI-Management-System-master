package api.v1.KPI.Management.System.employee.controller.manager;


import api.v1.KPI.Management.System.employee.service.core.EmployeeCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee-manager")
public class EmployeeManagerController {
    @Autowired
    private EmployeeCoreService employeeCoreService;

    public static int getCurrentPage(Integer page) {
        return (page != null && page > 0) ? page - 1 : 0;
    }

}
