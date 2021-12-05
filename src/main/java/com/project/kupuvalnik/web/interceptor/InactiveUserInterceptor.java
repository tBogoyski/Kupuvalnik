package com.project.kupuvalnik.web.interceptor;


import com.project.kupuvalnik.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class InactiveUserInterceptor implements HandlerInterceptor {

    private final UserService userService;
    @Autowired
    private HttpSession session;

    public InactiveUserInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        userService.logOutInactiveUser(request, response, session);
        return true;
    }
}
