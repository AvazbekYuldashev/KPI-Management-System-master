package api.v1.KPI.Management.System.application.service;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.util.AppResponseUtil;
import api.v1.KPI.Management.System.application.dto.core.ApplicationResponseDTO;
import api.v1.KPI.Management.System.application.entity.ApplicationEntity;
import api.v1.KPI.Management.System.application.enums.ApplicationStatus;
import api.v1.KPI.Management.System.application.mapper.ApplicationMapper;
import api.v1.KPI.Management.System.application.repository.ApplicationRepository;
import api.v1.KPI.Management.System.offering.entity.OfferingEntity;
import api.v1.KPI.Management.System.offering.service.OfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private ApplicationMapper applicationMapper;
    @Autowired
    private OfferingService offeringService;

    public ApplicationEntity create(ApplicationEntity entity){
        OfferingEntity offering = offeringService.findById(entity.getOfferingId());
        entity.setKpiBallLimit(offering.getKpiBall());
        entity.setDeadline(offering.getDeadline());
        return applicationRepository.save(entity);
    }

    public ApplicationEntity findById(String id){
        Optional<ApplicationEntity> optional = applicationRepository.findById(id);
        if (optional.isEmpty()){
            throw new RuntimeException("Application with id " + id + " not found");
        }
        return optional.get();
    }

    public Page<ApplicationResponseDTO> findAll(int page, Integer size){
        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ApplicationEntity> pageObj = applicationRepository.findAllPage(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        List<ApplicationResponseDTO> response = pageObj.getContent().stream().map(applicationMapper::toResponseDTO).collect(Collectors.toList());
        long total = pageObj.getTotalElements();
        return new PageImpl<>(response, pageable, total);
    }


    public Page<ApplicationResponseDTO> findAllApprove(int page, Integer size) {
        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ApplicationEntity> pageObj = applicationRepository.findAllPageByStatus(ApplicationStatus.APPROVED, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        List<ApplicationResponseDTO> response = pageObj.getContent().stream().map(applicationMapper::toResponseDTO).collect(Collectors.toList());
        long total = pageObj.getTotalElements();
        return new PageImpl<>(response, pageable, total);
    }

    public AppResponse<String> changeStatus(String id, ApplicationStatus status){
        int effectedRow = applicationRepository.updateStatus(id, status);
        return AppResponseUtil.chek(effectedRow > 0);
    }

    public AppResponse<String> adminChecked(String id, ApplicationStatus status){
        int effectedRow = applicationRepository.adminChecked(id, status, LocalDateTime.now());
        return AppResponseUtil.chek(effectedRow > 0);
    }

    public AppResponse<String> employeeApproved(String id, ApplicationStatus status , Long deadline) {
        LocalDateTime now = LocalDateTime.now();
        int effectedRow = applicationRepository.employeeApproved(id, status, now, now.plusDays(deadline));
        return AppResponseUtil.chek(effectedRow > 0);
    }

    public AppResponse<String> employeeEnd(String id, ApplicationStatus status) {
        int effectedRow = applicationRepository.employeeEnd(id, status, LocalDateTime.now());
        return AppResponseUtil.chek(effectedRow > 0);
    }

    public AppResponse<String> updatedKPI(String id, ApplicationStatus status, float kpi) {
        int effectedRow = applicationRepository.updatedKPI(id, status, LocalDateTime.now(), kpi);
        return AppResponseUtil.chek(effectedRow > 0);
    }
}
