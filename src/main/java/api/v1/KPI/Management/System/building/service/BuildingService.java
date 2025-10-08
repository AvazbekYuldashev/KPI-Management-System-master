package api.v1.KPI.Management.System.building.service;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.building.dto.BuildingResponseDTO;
import api.v1.KPI.Management.System.building.dto.admin.BuildingAdminUpdateDTO;
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

    public BuildingEntity create(BuildingEntity entity) {
        return buildingRepository.save(entity);
    }

    public BuildingResponseDTO getById(String id) {
        return buildingMapper.toResponseDTO(findById(id));
    }

    public BuildingEntity findById(String id) {
        Optional<BuildingEntity> optional = buildingRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("Building with id " + id + " not found");
        }
        return optional.get();
    }

    public AppResponse<String> deleteById(String id) {
        BuildingEntity entity = findById(id);
        int effectedRow = buildingRepository.deleteSoft(entity.getId());
        if (effectedRow > 0) {
            return new AppResponse<>("Building with id " + id + " deleted successfully");
        }
        return new AppResponse<>("Building with id " + id + " not found");
    }

    public AppResponse<String> updateDetail(String id, BuildingEntity entity) {
        findById(id);
        int effectedRow = buildingRepository.updateDetail(id, entity.getTitle(), entity.getDescription(), entity.getChiefId(), entity.getDepartmentId(), LocalDateTime.now());
        if (effectedRow > 0) {
            return new AppResponse<>("Building with id " + id + " updated successfully");
        }
        return new AppResponse<>("Building with id " + id + " not found");
    }

    public PageImpl<BuildingResponseDTO> getAll(int page, int size) {
        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<BuildingEntity> pageObj = buildingRepository.findAllPage(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        List<BuildingResponseDTO> response = pageObj.getContent().stream().map(buildingMapper::toResponseDTO).collect(Collectors.toList());
        long total = pageObj.getTotalElements();
        return new PageImpl<>(response, pageable, total);
    }

    public PageImpl<BuildingResponseDTO> getByDepartmentId(int page, int size, String id) {
        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<BuildingEntity> pageObj = buildingRepository.findAllByDepartmentId(id, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        List<BuildingResponseDTO> response = pageObj.getContent().stream().map(buildingMapper::toResponseDTO).collect(Collectors.toList());
        long total = pageObj.getTotalElements();
        return new PageImpl<>(response, pageable, total);
    }
}
