package com.project.kupuvalnik.service;

import com.project.kupuvalnik.models.service.UserRegisterServiceModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public interface UserService {
    boolean isUsernameFree(String userName);

    void registerAndLoginUser(UserRegisterServiceModel userRegisterServiceModel);

    void initializeUsersAndRoles();

    void logOutInactiveUser(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException;
}

