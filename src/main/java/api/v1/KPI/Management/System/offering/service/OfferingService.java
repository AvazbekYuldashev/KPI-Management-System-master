package api.v1.KPI.Management.System.offering.service;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.util.AppResponseUtil;
import api.v1.KPI.Management.System.category.dto.core.CategoryResponseDTO;
import api.v1.KPI.Management.System.category.entity.CategoryEntity;
import api.v1.KPI.Management.System.exception.exps.ResourceNotFoundException;
import api.v1.KPI.Management.System.offering.dto.core.OfferingResponseDTO;
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


    public OfferingEntity create(OfferingEntity entity){
        return offeringRepository.save(entity);
    }

    public OfferingEntity findById(String id){
        Optional<OfferingEntity> optional = offeringRepository.findByIdAndVisibleTrue(id);
        if (optional.isEmpty()){
            throw new ResourceNotFoundException("Offering with id " + id + " not found");
        }
        return optional.get();
    }

    public Page<OfferingResponseDTO> findAll(int page, Integer size){
        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<OfferingEntity> pageObj = offeringRepository.findAllPage(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        List<OfferingResponseDTO> response = pageObj.getContent().stream().map(offeringMapper::toResponseDTO).collect(Collectors.toList());
        long total = pageObj.getTotalElements();
        return new PageImpl<>(response, pageable, total);
    }
    public AppResponse<String> changeDetail(OfferingEntity entity){
        int effectedRow = offeringRepository.changeDetail(entity.getId(), entity.getTitle(), entity.getDescription(), entity.getKpiBall(), entity.getCategoryId(), LocalDateTime.now());
        return AppResponseUtil.chek(effectedRow > 0);
    }

    public AppResponse<String> deleteById(String id) {
        int effectedRow = offeringRepository.deleteSoftById(id, false);
        return AppResponseUtil.chek(effectedRow > 0);
    }

    public Page<OfferingResponseDTO> findByCategoryId(String id, int page, Integer size){
        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<OfferingEntity> pageObj = offeringRepository.findByCategoryIdPage(id, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        List<OfferingResponseDTO> response = pageObj.getContent().stream().map(offeringMapper::toResponseDTO).collect(Collectors.toList());
        long total = pageObj.getTotalElements();
        return new PageImpl<>(response, pageable, total);
    }

}
