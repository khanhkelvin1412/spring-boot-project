package com.evotek.service;

import com.evotek.dto.CategoryDTO;
import com.evotek.dto.NewDTO;
import com.evotek.entity.NewEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface INewService {
    NewDTO save(NewDTO newDTO);

    void delete(long ids);

    int totalItem();

    List<NewDTO> findAll(Pageable pageable);

    List<NewDTO> findAll();

    NewDTO findNewById(long id);
}
