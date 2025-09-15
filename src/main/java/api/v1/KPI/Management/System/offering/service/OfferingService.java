package api.v1.KPI.Management.System.offering.service;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.exception.exps.ResourceNotFoundException;
import api.v1.KPI.Management.System.offering.dto.OfferingResponseDTO;
import api.v1.KPI.Management.System.offering.dto.admin.OfferingAdminUpdateDTO;
import api.v1.KPI.Management.System.offering.entity.OfferingEntity;
import api.v1.KPI.Management.System.offering.mapper.OfferingMapper;
import api.v1.KPI.Management.System.offering.repository.OfferingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferingService {
    @Autowired
    private OfferingRepository offeringRepository;
    @Autowired
    private OfferingMapper offeringMapper;

    public OfferingEntity create(OfferingEntity entity) {
        return offeringRepository.save(entity);
    }

    public OfferingEntity findById(String id) {
        Optional<OfferingEntity> optional = offeringRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("Offering with id " + id + " not found");
        }
        return optional.get();
    }

    public AppResponse<String> deleteById(String id) {
        OfferingEntity entity = findById(id);
        int effectedRow = offeringRepository.deleteSoft(entity.getId());
        if (effectedRow > 0) {
            return new AppResponse<>("Offering with id " + id + " deleted successfully");
        }
        return new AppResponse<>("Offering with id " + id + " not found");
    }

    public AppResponse<String> updateDetail(String id, OfferingAdminUpdateDTO dto) {
        findById(id);
        int EffectedRow = offeringRepository.updateDetail(id, dto.getTitle(), dto.getDescription(), dto.getCategoryId(), dto.getKpiBall(), LocalDateTime.now());
        if (EffectedRow > 0) {
            return new AppResponse<>("Offering with id " + id + " updated successfully");
        }
        return new AppResponse<>("Offering0 with id " + id + " not found");
    }

    public PageImpl<OfferingResponseDTO> getAll(int page, int size) {
        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<OfferingEntity> pageObj = offeringRepository.findAllPage(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        List<OfferingResponseDTO> response = pageObj.getContent().stream().map(offeringMapper::toResponseDTO).collect(Collectors.toList());
        long total = pageObj.getTotalElements();
        return new PageImpl<>(response, pageable, total);
    }


}
