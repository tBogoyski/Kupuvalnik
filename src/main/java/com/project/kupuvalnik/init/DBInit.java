package com.project.kupuvalnik.init;

import com.project.kupuvalnik.service.OfferService;
import com.project.kupuvalnik.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {
    private final UserService userService;
    private final OfferService offerService;

    public DBInit(UserService userService, OfferService offerService) {
        this.userService = userService;
        this.offerService = offerService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.initializeUsersAndRoles();
        offerService.initializeOffers();

    }
}
