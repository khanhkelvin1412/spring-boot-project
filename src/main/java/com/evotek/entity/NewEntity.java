package com.evotek.entity;

import javax.persistence.*;


@Entity(name = "new")
@Table
public class NewEntity extends GenericEntity{
    @Column(name = "title", length = 1000)
    private String title;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "shortdescription", length = 1000)
    private String shortDescription;

    @Column(name = "content", length = 20000)
    private String content;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryEntity;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CategoryEntity getCategoryEntity() {
        return categoryEntity;
    }

    public void setCategoryEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }
}
