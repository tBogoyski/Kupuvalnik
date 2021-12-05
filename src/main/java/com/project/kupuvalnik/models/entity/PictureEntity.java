package com.project.kupuvalnik.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "pictures")
public class PictureEntity extends BaseEntity {

    private String url;
    private String publicId;

    public PictureEntity() {
    }

    @Column(nullable = false)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column
    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }
}
