package com.test.payment_test.controller;

import com.test.payment_test.modul.CashB;
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
        model.addAttribute("getMoney", new CashB());
        return "getMoney";
    }


    @GetMapping("/search/{keyword}")
    public String search(@PathVariable String keyword, Model model) {
       CashB results = service.getMoneyUser(keyword);
        model.addAttribute("results", results);
        return "viewMoneyRecipient";
    }

//    @GetMapping("/get")
//    @PermitAll
//    public String getMoneys(Model model,CashB cashB) {
//        model.addAttribute("code",service.getMoneyUser(cashB.getUniqueCode()));
//        return "viewMoneyRecipient";
//
//    }

    }

