package api.v1.KPI.Management.System.category.service;

import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.category.dto.CategoryResponseDTO;
import api.v1.KPI.Management.System.category.entity.CategoryEntity;
import api.v1.KPI.Management.System.category.mapper.CategoryMapper;
import api.v1.KPI.Management.System.category.repository.CategoryRepository;
import api.v1.KPI.Management.System.category.service.admin.CategoryAdminUpdateDTO;
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

    public CategoryEntity create(CategoryEntity entity) {
        return categoryRepository.save(entity);
    }

    public CategoryEntity findById(String id) {
        Optional<CategoryEntity> optional = categoryRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("Category with id " + id + " not found");
        }
        return optional.get();
    }

    public AppResponse<String> deleteById(String id) {
        CategoryEntity entity = findById(id);
        int effectedRow = categoryRepository.deleteSoft(entity.getId());
        if (effectedRow > 0) {
            return new AppResponse<>("Category with id " + id + " deleted successfully");
        }
        return new AppResponse<>("Category with id " + id + " not found");
    }

    public AppResponse<String> updateDetail(String id, CategoryAdminUpdateDTO dto) {
        findById(id);
        int effectedRow = categoryRepository.updateDetail(id, dto.getTitle(), dto.getDescription(), LocalDateTime.now());
        if (effectedRow > 0) {
            return new AppResponse<>("Category with id " + id + " updated successfully");
        }
        return new AppResponse<>("Category with id " + id + " not found");
    }

    public PageImpl<CategoryResponseDTO> getAll(int page, int size) {
        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<CategoryEntity> pageObj = categoryRepository.findAllPage(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        List<CategoryResponseDTO> response = pageObj.getContent().stream().map(categoryMapper::toResponseDTO).collect(Collectors.toList());
        long total = pageObj.getTotalElements();
        return new PageImpl<>(response, pageable, total);
    }

}
