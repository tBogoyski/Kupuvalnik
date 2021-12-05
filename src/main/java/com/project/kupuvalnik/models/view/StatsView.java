package com.project.kupuvalnik.models.view;

public class StatsView {
    private final int authRequest;
    private final int anonRequest;
    private final int authResponse;
    private final int anonResponse;

    public StatsView(int authRequest, int anonRequest, int authResponse, int anonResponse) {
        this.authRequest = authRequest;
        this.anonRequest = anonRequest;
        this.authResponse = authResponse;
        this.anonResponse = anonResponse;
    }

    public int getTotalRequests() {
        return authRequest + anonRequest;
    }

    public int getTotalResponse() {
        return authResponse + anonResponse;
    }

    public int getAuthResponse() {
        return authResponse;
    }

    public int getAnonResponse() {
        return anonResponse;
    }

    public int getAuthRequest() {
        return authRequest;
    }

    public int getAnonRequest() {
        return anonRequest;
    }
}
