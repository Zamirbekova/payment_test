package com.test.payment_test.controller;

import com.test.payment_test.modul.User;
import com.test.payment_test.service.UserService;
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
    public String getMoney(Model model) {
        model.addAttribute("getMoney", new User());
        return "getMoney";
    }

    @PostMapping("/save/{code}")
    public String getMoneySave(Model model, @PathVariable String code) {
     model.addAttribute("getMoney",service.findByCode(code));
     return "getMoney";
    }
}