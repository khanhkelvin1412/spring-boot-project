package com.evotek.service.impl;

import com.evotek.converter.NewConverter;
import com.evotek.dto.NewDTO;
import com.evotek.entity.CategoryEntity;
import com.evotek.entity.NewEntity;
import com.evotek.repository.CategoryRepository;
import com.evotek.repository.NewRepository;
import com.evotek.service.INewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewService implements INewService {
    @Autowired
    private NewRepository newRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private NewConverter converter;

    @Override
    public NewDTO save(NewDTO newDTO) {
        NewEntity newEntity = new NewEntity();
        if(newDTO.getId() != null){
            newEntity = newRepository.findOne(newDTO.getId());
            converter.toEntity(newEntity, newDTO);
            newEntity.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        }else {
            newEntity = converter.toEntity(newDTO);
            newEntity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        }
        CategoryEntity categoryEntity = categoryRepository.findOneByCode(newDTO.getCategoryCode());
        newEntity.setCategoryEntity(categoryEntity);
        newRepository.save(newEntity);
        return converter.toNewDTO(newEntity);
    }

    @Override
    public void delete(long ids) {
        newRepository.delete(ids);
    }

    @Override
    public int totalItem() {
        return (int) newRepository.count();
    }

    @Override
    public List<NewDTO> findAll(Pageable pageable) {
        List<NewDTO> results = new ArrayList<>();
        List<NewEntity> entityList = (List<NewEntity>) newRepository.findAll(pageable).getContent();
        for(NewEntity item : entityList){
            results.add(converter.toNewDTO(item));
        }
        return results;
    }

    @Override
    public List<NewDTO> findAll() {
        List<NewDTO> results = new ArrayList<>();
        List<NewEntity> entityList = newRepository.findAll();
        for(NewEntity item : entityList){
            results.add(converter.toNewDTO(item));
        }
        return results;
    }

    @Override
    public NewDTO findNewById(long id) {
         return converter.toNewDTO(newRepository.findOne(id));
    }




 /*   @Override
    public NewDTO update(NewDTO newDTO) {
        NewEntity oldNew = newRepository.findOne(newDTO.getId());
        converter.toEntity(oldNew, newDTO);
        oldNew.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        CategoryEntity categoryEntity = categoryRepository.findOneByCode(newDTO.getCategoryCode());
        oldNew.setCategoryEntity(categoryEntity);
        newRepository.save(oldNew);
        return converter.toNewDTO(oldNew);
    }
  */
}
