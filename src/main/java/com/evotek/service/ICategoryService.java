package com.evotek.service;

import com.evotek.dto.CategoryDTO;

import java.util.List;

public interface ICategoryService {
    CategoryDTO findOneById(long id);

    List<CategoryDTO> findAll();
}
