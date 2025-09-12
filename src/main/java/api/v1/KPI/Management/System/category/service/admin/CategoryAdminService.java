package api.v1.KPI.Management.System.category.service.admin;

import api.v1.KPI.Management.System.category.mapper.CategoryMapper;
import api.v1.KPI.Management.System.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryAdminService extends CategoryService{
    @Autowired
    private CategoryMapper categoryMapper;



}
