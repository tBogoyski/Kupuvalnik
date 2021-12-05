package com.project.kupuvalnik.models.service;

import com.project.kupuvalnik.models.entity.PictureEntity;
import com.project.kupuvalnik.models.entity.enums.OfferCategoryEnum;

import java.math.BigDecimal;
import java.util.Set;

public class OfferUpdateServiceModel {

    private Long id;
    private String name;
    private OfferCategoryEnum category;
    private Set<PictureEntity> pictures;
    private BigDecimal price;
    private String description;

    public OfferUpdateServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OfferCategoryEnum getCategory() {
        return category;
    }

    public void setCategory(OfferCategoryEnum category) {
        this.category = category;
    }

    public Set<PictureEntity> getPictures() {
        return pictures;
    }

    public void setPictures(Set<PictureEntity> pictures) {
        this.pictures = pictures;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
