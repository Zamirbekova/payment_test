package com.test.payment_test.controller;

import com.test.payment_test.modul.Recipient;
import com.test.payment_test.modul.Users;
import com.test.payment_test.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {


    private final UserService service;

    @PreAuthorize("hasAnyAuthority('RECIPIENT')")
    @GetMapping("/getMoney")
    public String getMoney(Model model) {
        model.addAttribute("getMoney", new Users());
        return "getMoney";
    }

    @PostMapping("/save")
    public String getMoneySave(Model model, Recipient recipient) {
//     model.addAttribute("getMoney",service.findByCode(code));
        model.addAttribute("getMoney", service.getMoney(recipient));
        return "viewMoneyRecipient";

    }

    @GetMapping("/save")
    public String getMoneys(Model model) {
//     model.addAttribute("getMoney",service.findByCode(code));
        model.addAttribute("getMoney", service.getMoney());
        return "viewMoneyRecipient";

    }
}
