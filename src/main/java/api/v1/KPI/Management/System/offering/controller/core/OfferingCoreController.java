package api.v1.KPI.Management.System.offering.controller.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/offering")
@PreAuthorize("hasRole('USER')")
public class OfferingCoreController {
    @Autowired

}
