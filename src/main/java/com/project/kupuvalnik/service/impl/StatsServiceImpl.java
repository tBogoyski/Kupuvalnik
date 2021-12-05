package com.project.kupuvalnik.service.impl;

import com.project.kupuvalnik.models.view.StatsView;
import com.project.kupuvalnik.service.StatsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class StatsServiceImpl implements StatsService {

    private int anonymousRequests, authRequests, anonymousResponse, authResponse;


    @Override
    public void onRequest() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && (authentication.getPrincipal() instanceof UserDetails)) {
            authRequests++;
        } else {
            anonymousRequests++;
        }


    }

    @Override
    public void onResponse() {
        Authentication authentication = SecurityContextHolder.
                getContext().
                getAuthentication();

        if (authentication != null && (authentication.getPrincipal() instanceof UserDetails)) {
            authResponse++;
        } else {
            anonymousResponse++;
        }
    }

    @Override
    public StatsView getStats() {
        return new StatsView(authRequests, anonymousRequests, anonymousResponse, authResponse);

    }
}
