package api.v1.KPI.Management.System.building.service;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.util.AppResponseUtil;
import api.v1.KPI.Management.System.building.dto.core.BuildingResponseDTO;
import api.v1.KPI.Management.System.building.entity.BuildingEntity;
import api.v1.KPI.Management.System.building.mapper.BuildingMapper;
import api.v1.KPI.Management.System.building.repository.BuildingRepository;
import api.v1.KPI.Management.System.exception.exps.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private BuildingMapper buildingMapper;

    public BuildingEntity create(BuildingEntity entity){
        return buildingRepository.save(entity);
    }

    public BuildingEntity findById(String id){
        Optional<BuildingEntity> optional = buildingRepository.findByIdAndVisibleTrue(id);
        if (optional.isEmpty()){
            throw new ResourceNotFoundException("Building with id " + id + " not found");
        }
        return optional.get();
    }

    public AppResponse<String> changeDetail(BuildingEntity entity){
        int effectedRow = buildingRepository.changeDetail(entity.getId(), entity.getTitle(), entity.getDescription(), entity.getChiefId(), entity.getDepartmentId(), LocalDateTime.now());
        return AppResponseUtil.chek(effectedRow > 0);
    }

    public AppResponse<String> deleteById(String id) {
        int effectedRow = buildingRepository.deleteSoftById(id, false);
        return AppResponseUtil.chek(effectedRow > 0);
    }

    public Page<BuildingResponseDTO> findAll(int page, Integer size){
        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<BuildingEntity> pageObj = buildingRepository.findAllPage(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        List<BuildingResponseDTO> response = pageObj.getContent().stream().map(buildingMapper::toResponseDTO).collect(Collectors.toList());
        long total = pageObj.getTotalElements();
        return new PageImpl<>(response, pageable, total);
    }

    public Page<BuildingResponseDTO> findByDepartmentId(String id, int page, Integer size){
        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<BuildingEntity> pageObj = buildingRepository.findByDepartmentIdPage(id, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        List<BuildingResponseDTO> response = pageObj.getContent().stream().map(buildingMapper::toResponseDTO).collect(Collectors.toList());
        long total = pageObj.getTotalElements();
        return new PageImpl<>(response, pageable, total);
    }
}
