package api.v1.KPI.Management.System.application.service.employee;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.application.dto.core.ApplicationResponseDTO;
import api.v1.KPI.Management.System.application.entity.ApplicationEntity;
import api.v1.KPI.Management.System.application.enums.ApplicationStatus;
import api.v1.KPI.Management.System.application.service.ApplicationService;
import api.v1.KPI.Management.System.exception.exps.AppBadException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class ApplicationEmployeeService extends ApplicationService {

    public Page<ApplicationResponseDTO> getAll(int page, Integer size, AppLanguage lang) {
        return findAllApprove(page, size);
    }

    public AppResponse<String> approve(String id, AppLanguage lang) {
        ApplicationEntity entity = findById(id);
        if (!entity.getStatus().equals(ApplicationStatus.APPROVED)){
            throw new AppBadException("Application with id " + id + " is not sent");
        }
        return employeeApproved(id, ApplicationStatus.IN_PROGRESS, entity.getDeadline());
    }

    public AppResponse<String> review(String id, AppLanguage lang) {
        ApplicationEntity entity = findById(id);
        if (!entity.getStatus().equals(ApplicationStatus.IN_PROGRESS)){
            throw new AppBadException("Application with id " + id + " is not sent");
        }
        return employeeEnd(id, ApplicationStatus.REVIEW);
    }


    public AppResponse<String> complete(String id, AppLanguage lang) {
        ApplicationEntity entity = findById(id);
        if (!entity.getStatus().equals(ApplicationStatus.REVIEW)) {
            throw new AppBadException("Application with id " + id + " is not sent");
        }

        LocalDateTime employeeApprovedDate = entity.getEmployeeApprovedDate();
        LocalDateTime employeeEndDate = entity.getEmployeeEndDate();
        LocalDateTime limitDate = entity.getLimitDate();
        Float kpiBallLimit = entity.getKpiBallLimit();

        // Real ish davomiyligi va limit o'rtasidagi farq (kunlarda)
        long realDurationDays = Duration.between(employeeApprovedDate, employeeEndDate).toDays();
        long limitDurationDays = Duration.between(employeeApprovedDate, limitDate).toDays();

        long delayDays = realDurationDays - limitDurationDays;
        float kpiBall = kpiBallLimit;
        if (delayDays <= 0) {
            System.out.println("Ish vaqtida yakunlandi");

            // KPI to'liq saqlanadi
        } else {
            System.out.println("Ish kechikib yakunlandi, " + delayDays + " kun kechikkan");
            // Har 1 kun uchun KPI 10% kamayadi
            float penalty = delayDays * 0.1f; // 1 kun = 10%
            // KPI manfiy bo‘lishi mumkin — cheklovsiz
            kpiBall = kpiBallLimit * (1 - penalty);
        }

        return updatedKPI(id, ApplicationStatus.COMPLETED, kpiBall);
    }




}
