package com.project.kupuvalnik.web;

import com.project.kupuvalnik.service.StatsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StatsController {

    private final StatsService statsService;


    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/stats")
    public ModelAndView stats() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("stats", statsService.getStats());
        modelAndView.setViewName("admin-stats");
        return modelAndView;
    }
}
