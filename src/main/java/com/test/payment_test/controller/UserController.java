package com.test.payment_test.controller;

import com.test.payment_test.modul.Users;
import com.test.payment_test.service.UserService;
import jakarta.annotation.security.PermitAll;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {


    private final UserService service;

    @GetMapping("/getMoney")
    @PermitAll
    public String getMoney(Model model) {
        model.addAttribute("getMoney", new Users());
        return "getMoney";
    }

    @PostMapping("/save")
    @PermitAll
    public String getMoneySave(Model model, @RequestParam("code") String code) {
        model.addAttribute("code", service.getMoney(code));
        return "viewMoneyRecipient";

    }

    @GetMapping("/save")
    @PermitAll
    public String getMoneys(Model model, @RequestParam String code) {
        model.addAttribute("code",service.getMoney(code));
        return "viewMoneyRecipient";

    }
    @GetMapping
    @PermitAll
    public String getMon(Model model){
        model.addAttribute("mon",service.getAll());
        return "getAllUsers";
        }

    }

