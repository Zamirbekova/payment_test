package com.test.payment_test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/payment")
@Controller
public class PageController {
    @GetMapping
    public String page(){
        return "page";
    }

}
