package com.project.kupuvalnik.models.entity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "users")
@Entity
public class UserEntity extends BaseEntity {

    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private boolean isActive;
    private Set<UserRoleEntity> roles = new HashSet<>();
    private Set<OfferEntity> favorites;
    private String phone;

    public UserEntity() {
    }

    @Column(nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(nullable = false)
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public Set<UserRoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRoleEntity> roles) {
        this.roles = roles;
    }

    @OneToMany
    public Set<OfferEntity> getFavorites() {
        return favorites;
    }

    public void setFavorites(Set<OfferEntity> favorites) {
        this.favorites = favorites;
    }

    @Column(nullable = false)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
