package com.project.kupuvalnik.models.view;

import com.project.kupuvalnik.models.entity.enums.OfferCategoryEnum;

import java.math.BigDecimal;

public class OfferDetailsView {

    private Long id;
    private String name;
    private OfferCategoryEnum category;
    private String pictureUrl;
    private BigDecimal price;
    private String description;
    private String city;
    private String country;
    private String phone;
    private String sellerName;
    private boolean canDeleteAndUpdate;

    public OfferDetailsView() {
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

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public boolean isCanDeleteAndUpdate() {
        return canDeleteAndUpdate;
    }

    public void setCanDeleteAndUpdate(boolean canDeleteAndUpdate) {
        this.canDeleteAndUpdate = canDeleteAndUpdate;
    }
}
