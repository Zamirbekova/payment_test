package com.test.payment_test;

import com.test.payment_test.exception.UserAlreadyExistException;
import com.test.payment_test.modul.Users;
import com.test.payment_test.role.Role;
import com.test.payment_test.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class PaymentTestApplication {


    public static void main(String[] args) {
        SpringApplication.run(PaymentTestApplication.class, args);
    }

    private final UserService userService;

    @PostConstruct
    public void init() throws UserAlreadyExistException {
        Users users = new Users();
        users.setRole(Role.USER);
        users.setEmail("jimi@gmail.com");
        users.setPassword("hadicha");
        userService.registerNewUserAccount(users);
        Users users1 = new Users();
        users1.setEmail("zamirbekovahadica@gmail.com");
        users1.setRole(Role.USER);
        users1.setPassword("hadicha");
        userService.registerNewUserAccount(users1);
    }

}
