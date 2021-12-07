package com.project.kupuvalnik.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/users/profile")
    public String profileOverview() {
        return "under-construction";
    }

    @GetMapping("/users/favorites")
    public String userFavoriteOffers() {
        return "under-construction";
    }

    @GetMapping("/users/owned")
    public String offersAddedByTheUser() {
        return "under-construction";
    }

}
