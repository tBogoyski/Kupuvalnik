package com.project.kupuvalnik.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MessagesController {

    @GetMapping("/messages")
    public String allMessages() {
        return "under-construction";
    }

}
