package com.evotek.converter;

import com.evotek.dto.NewDTO;
import com.evotek.entity.NewEntity;
import org.springframework.stereotype.Component;

import javax.persistence.Convert;
import javax.persistence.Converter;

@Component
public class NewConverter {
    public NewEntity toEntity(NewDTO dto){
        NewEntity entity = new NewEntity();
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setThumbnail(dto.getThumbnail());
        entity.setShortDescription(dto.getShortDescription());
        return entity;
    }

    public NewDTO toNewDTO(NewEntity entity){
        NewDTO dto = new NewDTO();
        if(entity.getId() != null){
            dto.setId(entity.getId());
        }
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setThumbnail(entity.getThumbnail());
        dto.setCategoryCode(entity.getCategoryEntity().getCode());
        dto.setShortDescription(entity.getShortDescription());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setModifiedBy(entity.getModifiedBy());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCreatedBy(entity.getModifiedBy());
        return dto;
    }

    public NewEntity toEntity(NewEntity entity, NewDTO dto){
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setThumbnail(dto.getThumbnail());
        entity.setShortDescription(dto.getShortDescription());
        return entity;
    }

}
