package com.test.payment_test.controller;

import com.test.payment_test.modul.CashA;
import com.test.payment_test.service.CashService;
import com.test.payment_test.service.UserService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sendMoney")
public class CashAController {
    private final CashService service;
    private final UserService userService;
    @Autowired
    public CashAController(CashService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping("/new")
    @PreAuthorize("hasAnyAuthority('SENDER')")
    public String sendmoney(Model model) {
        model.addAttribute("sendMoney", new CashA());
        return "sendMoney";
    }

    @PostMapping("/save")
    @PermitAll
    public String saveMoney(@ModelAttribute("sendMoney") CashA cashA) {
        service.sendMoney(cashA);
        return "redirect:/sendMoney/allCashs";
    }

    @GetMapping("/allCashs")
    public String getAllCashs(Model model){
    model.addAttribute("allCashs",service.getAll());
        return "mainPage";
    }

}

