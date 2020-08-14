package com.evotek.converter;

import com.evotek.dto.CategoryDTO;
import com.evotek.dto.NewDTO;
import com.evotek.entity.CategoryEntity;
import com.evotek.entity.NewEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryConvert {

    public CategoryDTO toCategoryDTO(CategoryEntity entity){
        CategoryDTO dto = new CategoryDTO();
        dto.setName(entity.getName());
        dto.setCode(entity.getCode());
        return dto;
    }
}
