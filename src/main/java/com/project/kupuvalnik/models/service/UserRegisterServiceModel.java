package com.project.kupuvalnik.models.service;

public class UserRegisterServiceModel {

    private String firstName;
    private String lastName;
    private String password;
    private String username;
    private String phone;

    public UserRegisterServiceModel() {
    }

    public String getFirstName() {
        return username != null ? username.trim() : null;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
