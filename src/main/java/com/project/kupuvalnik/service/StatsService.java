package com.project.kupuvalnik.service;

import com.project.kupuvalnik.models.view.StatsView;

public interface StatsService {
    void onRequest();

    void onResponse();

    StatsView getStats();
}
