package com.project.kupuvalnik.models.binding;

import com.project.kupuvalnik.models.entity.enums.OfferCategoryEnum;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class OfferAddBindingModel {

    private Long id;
    private String name;
    private OfferCategoryEnum category;
    private MultipartFile picture;
    private BigDecimal price;
    private String description;
    private String city;
    private String country;
    private String phone;

    public OfferAddBindingModel() {
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
    @NotBlank
    @Size(min = 15)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull
    @Size(min = 3, max = 20)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @NotNull
    @Size(min = 3, max = 20)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @NotNull
    @Min(10)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
