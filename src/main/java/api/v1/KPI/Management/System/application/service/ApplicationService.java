package api.v1.KPI.Management.System.application.service;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.application.dto.ApplicationDTO;
import api.v1.KPI.Management.System.application.dto.ApplicationResponseDTO;
import api.v1.KPI.Management.System.application.dto.admin.ApplicationAdminUpdateDTO;
import api.v1.KPI.Management.System.application.entity.ApplicationEntity;
import api.v1.KPI.Management.System.application.mapper.ApplicationMapper;
import api.v1.KPI.Management.System.application.repository.ApplicationRepository;
import api.v1.KPI.Management.System.exception.exps.ResourceNotFoundException;
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

    public ApplicationEntity create(ApplicationEntity entity) {
        return applicationRepository.save(entity);
    }

    public ApplicationEntity findById(String id) {
        Optional<ApplicationEntity> optional = applicationRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("Application with id " + id + " not found");
        }
        return optional.get();
    }

    public AppResponse<String> deleteById(String id) {
        ApplicationEntity entity = findById(id);
        int effectedRow = applicationRepository.deleteSoft(entity.getId());
        if (effectedRow > 0) {
            return new AppResponse<>("Application with id " + id + " deleted successfully");
        }
        return new AppResponse<>("Application with id " + id + " not found");
    }

    public AppResponse<String> updateDetail(String id, ApplicationDTO dto) {
        findById(id);
        int EffectedRow = applicationRepository.updateDetail(id, dto.getTitle(), dto.getDescription(), dto.getStatus(), dto.getSendProfileId(), dto.getAcceptorProfileId(), dto.getBuildingId(), LocalDateTime.now());
        if (EffectedRow > 0) {
            return new AppResponse<>("Application with id " + id + " updated successfully");
        }
        return new AppResponse<>("Application with id " + id + " not found");
    }

    public PageImpl<ApplicationResponseDTO> getAll(int page, int size) {
        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ApplicationEntity> pageObj = applicationRepository.findAllPage(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        List<ApplicationResponseDTO> response = pageObj.getContent().stream().map(applicationMapper::toResponseDTO).collect(Collectors.toList());
        long total = pageObj.getTotalElements();
        return new PageImpl<>(response, pageable, total);
    }

    public PageImpl<ApplicationResponseDTO> getAllMe(int page, int size, String profileId) {
        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ApplicationEntity> pageObj = applicationRepository.findAllByProfileId(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), profileId);
        List<ApplicationResponseDTO> response = pageObj.getContent().stream().map(applicationMapper::toResponseDTO).collect(Collectors.toList());
        long total = pageObj.getTotalElements();
        return new PageImpl<>(response, pageable, total);
    }
}
