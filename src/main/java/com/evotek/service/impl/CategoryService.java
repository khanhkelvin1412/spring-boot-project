package com.evotek.service.impl;

import com.evotek.converter.CategoryConvert;
import com.evotek.dto.CategoryDTO;
import com.evotek.entity.CategoryEntity;
import com.evotek.repository.CategoryRepository;
import com.evotek.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryConvert converter;

    @Override
    public CategoryDTO findOneById(long id) {
        return converter.toCategoryDTO(categoryRepository.findOne(id));
    }

    @Override
    public List<CategoryDTO> findAll() {
        List<CategoryDTO> list = new ArrayList<>();
        for(CategoryEntity entity : categoryRepository.findAll()){
            list.add(converter.toCategoryDTO(entity));
        }
        return list;
    }
}
