package com.test.payment_test.controller;

import com.test.payment_test.modul.CashA;
import com.test.payment_test.modul.CashB;
import com.test.payment_test.service.CashService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
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
@Autowired
    public CashAController(CashService service) {
        this.service = service;
    }


    @GetMapping("/new")
    @PermitAll
    public String sendmoney(Model model) {
        model.addAttribute("sendMoney", new CashA());
        return "sendMoney";
    }

    @PostMapping("/save")
    @PermitAll
    public String saveMoney(@ModelAttribute("sendMoney") CashB cashA) {
        service.sendMoney(cashA);
        return "redirect:/sendMoney/allCashs";
    }

    @GetMapping("/allCashs")
    @PermitAll
    public String getAllCashs(Model model) {
        model.addAttribute("allCashs", service.getAll());
        return "mainPage";
    }

}

