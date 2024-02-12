package com.test.payment_test.controller;

import com.test.payment_test.config.CustomUserDetailsService;
import com.test.payment_test.modul.Users;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//package com.test.payment_test.controller;
//
//import com.test.payment_test.modul.Users;
//import com.test.payment_test.service.UserService;
//import jakarta.annotation.security.PermitAll;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
////
//@Controller
//@RequestMapping("/login")
//public class AuthController {
//    //
//    private final UserService userService;
//
//    public AuthController(UserService userService) {
//        this.userService = userService;
//    }
//
//    //    private final RecipientService recipientService;
////
////    private final SecurityConfig config;
////
////    public AuthController(UserService userService, RecipientService recipientService, SecurityConfig config) {
////        this.userService = userService;
////        this.recipientService = recipientService;
////        this.config = config;
////    }
////
////    @PostMapping
////    @PermitAll
////    public String login(Model model) {
////        model.addAttribute("login", config.userDetailsUsersService());
////        model.addAttribute("login", config.userDetailsRecipientService());
////        model.addAttribute("login", config.userDetailsUsersService());
////        return "/login";
////    }
////
////    @GetMapping
////    @PermitAll
////    public String getLogin(Model model) {
////        model.addAttribute("login", config.userDetailsUsersService());
////        model.addAttribute("login", config.userDetailsRecipientService());
////        model.addAttribute("login", config.userDetailsUsersService());
////        return "/login";
////    }
////
////    @PostMapping("/register")
////    @PermitAll
////    public String register(Model module) {
//////        module.addAttribute("registerUsers", config.userDetailsRecipientService());
//////        module.addAttribute("registerRecipient", recipientService.register(recipient));
////        return "register";
////    }
////
////    @GetMapping("/register")
////    @PermitAll
////    public String resister(Model module) {
//////        module.addAttribute("registerUsers", new Users());
//////        module.addAttribute("registerRecipient", new Recipient());
////        return "register";
////    }
////}
//    @GetMapping
//    @PermitAll
//    public String registration(Model model) {
//        model.addAttribute("login", new Users());
//        return "login";
//    }
//
//    @PostMapping
//    @PermitAll
//    public String addUser(@ModelAttribute("login") Users login, Model model) {
////        if (bindingResult.hasErrors()) {
////            return "login";
////        }
////        if (!login.getPassword().equals(login.getPassword())){
////            model.addAttribute("login", login);
////            return "login";
////        }
////        if (!userService.saveUser(login)){
////            model.addAttribute("usernameError", login);
////            return "login";
////        }
//        model.addAttribute("login", userService.loadUserByUsername(login.getPhoneNumberSender()));
//        return "redirect:/user/save";
//    }
//}
@Controller
@RequestMapping(("/login"))
public class AuthController {
    @Autowired
    public AuthController(CustomUserDetailsService service) {
        this.service = service;
    }

    private final CustomUserDetailsService service;

    @GetMapping("/register")
    @PermitAll
    public String login(Model model) {
        model.addAttribute("login",new Users());
        return "login";
    }

    @PostMapping("/register")
    @PermitAll
    public String login(Model model, Users users) {
        model.addAttribute("login", service.loadUserByUsername(users.getPhoneNumberSender()));
        return "redirect:/user/save";
    }
}
