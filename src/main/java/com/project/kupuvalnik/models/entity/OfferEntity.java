package com.project.kupuvalnik.models.entity;

import com.project.kupuvalnik.models.entity.enums.OfferCategoryEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "offers")
public class OfferEntity extends BaseEntity {

    private String name;
    private OfferCategoryEnum category;
    private Set<PictureEntity> pictures;
    private BigDecimal price;
    private String description;
    private String city;
    private String country;
    private String phone;
    private UserEntity seller;

    public OfferEntity() {
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public OfferCategoryEnum getCategory() {
        return category;
    }

    public void setCategory(OfferCategoryEnum category) {
        this.category = category;
    }

    @OneToMany(fetch = FetchType.EAGER)
    public Set<PictureEntity> getPictures() {
        return pictures;
    }

    public void setPictures(Set<PictureEntity> pictures) {
        this.pictures = pictures;
    }

    @Column(nullable = false, columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(nullable = false)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(nullable = false)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(nullable = false)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @ManyToOne
    public UserEntity getSeller() {
        return seller;
    }

    public void setSeller(UserEntity seller) {
        this.seller = seller;
    }
}
