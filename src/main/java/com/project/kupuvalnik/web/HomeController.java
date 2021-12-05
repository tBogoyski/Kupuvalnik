package com.project.kupuvalnik.web;

import com.project.kupuvalnik.service.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final OfferService offerService;

    public HomeController(OfferService offerService) {
        this.offerService = offerService;
    }


    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("latestOffers", offerService.getLatestOffers());
    return "index";
    }




}
