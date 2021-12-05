package com.project.kupuvalnik.models.binding;

import com.project.kupuvalnik.models.entity.enums.OfferCategoryEnum;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class OfferUpdateBindingModel {

    private Long id;
    private String name;
    private OfferCategoryEnum category;
    private MultipartFile picture;
    private BigDecimal price;
    private String description;

    public OfferUpdateBindingModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @Size(min = 3, max = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    public OfferCategoryEnum getCategory() {
        return category;
    }

    public void setCategory(OfferCategoryEnum category) {
        this.category = category;
    }

    @NotNull
    public MultipartFile getPicture() {
        return picture;
    }

    public void setPicture(MultipartFile picture) {
        this.picture = picture;
    }

    @NotNull
    @PositiveOrZero
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @NotNull
    @Size(min = 15)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
