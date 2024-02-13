package com.test.payment_test.controller;

import com.test.payment_test.exception.UserAlreadyExistException;
import com.test.payment_test.modul.Users;
import com.test.payment_test.service.UserService;
import jakarta.annotation.security.PermitAll;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/registration")
    @PermitAll
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new Users());
        return "login";

    }

    @PostMapping("/registration")
    @PermitAll
    public String registerUserAccount(
            @ModelAttribute("user") @Valid Users userDto,
            Model model) throws UserAlreadyExistException {
        model.addAttribute("user", userService.registerNewUserAccount(userDto));
        return "page";
    }

    @GetMapping("/log")
    @PermitAll
    public String showLogin(Model model) {
        model.addAttribute("login", new Users());
        return "register";
    }

    @PostMapping("/log")
    @PermitAll
    public String login(@ModelAttribute("login") @Valid Users users,
                        Model model) {
        model.addAttribute("login", userService.loadUserByUsername(users.getEmail()));
        return "viewMoneyRecipient";
    }
}
