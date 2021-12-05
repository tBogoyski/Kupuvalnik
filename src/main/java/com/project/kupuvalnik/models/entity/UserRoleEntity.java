package com.project.kupuvalnik.models.entity;

import com.project.kupuvalnik.models.entity.enums.UserRoleEnum;

import javax.persistence.*;

@Table(name = "roles")
@Entity
public class UserRoleEntity extends BaseEntity {

    private UserRoleEnum role;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public UserRoleEnum getRole() {
        return role;
    }

    public void setRole(UserRoleEnum role) {
        this.role = role;
    }
}
