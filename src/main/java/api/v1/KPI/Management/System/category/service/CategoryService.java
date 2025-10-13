package api.v1.KPI.Management.System.category.service;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.util.AppResponseUtil;
import api.v1.KPI.Management.System.building.dto.core.BuildingResponseDTO;
import api.v1.KPI.Management.System.building.entity.BuildingEntity;
import api.v1.KPI.Management.System.category.dto.core.CategoryResponseDTO;
import api.v1.KPI.Management.System.category.entity.CategoryEntity;
import api.v1.KPI.Management.System.category.mapper.CategoryMapper;
import api.v1.KPI.Management.System.category.repository.CategoryRepository;
import api.v1.KPI.Management.System.exception.exps.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper;

    public CategoryEntity create(CategoryEntity entity){
        return categoryRepository.save(entity);
    }

    public CategoryEntity findById(String id){
        Optional<CategoryEntity> optional = categoryRepository.findByIdAndVisibleTrue(id);
        if (optional.isEmpty()){
            throw new ResourceNotFoundException("Category with id " + id + " not found");
        }
        return optional.get();
    }

    public Page<CategoryResponseDTO> findAll(int page, Integer size){
        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<CategoryEntity> pageObj = categoryRepository.findAllPage(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        List<CategoryResponseDTO> response = pageObj.getContent().stream().map(categoryMapper::toResponseDTO).collect(Collectors.toList());
        long total = pageObj.getTotalElements();
        return new PageImpl<>(response, pageable, total);
    }
    public AppResponse<String> changeDetail(CategoryEntity entity){
        int effectedRow = categoryRepository.changeDetail(entity.getId(), entity.getTitle(), entity.getDescription(), entity.getDepartmentId(), LocalDateTime.now());
        return AppResponseUtil.chek(effectedRow > 0);
    }

    public AppResponse<String> deleteById(String id) {
        int effectedRow = categoryRepository.deleteSoftById(id, false);
        return AppResponseUtil.chek(effectedRow > 0);
    }

    public Page<CategoryResponseDTO> findByDepartmentId(String id, int page, Integer size){
        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<CategoryEntity> pageObj = categoryRepository.findByDepartmentIdPage(id, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        List<CategoryResponseDTO> response = pageObj.getContent().stream().map(categoryMapper::toResponseDTO).collect(Collectors.toList());
        long total = pageObj.getTotalElements();
        return new PageImpl<>(response, pageable, total);
    }
}
