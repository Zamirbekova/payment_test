package com.test.payment_test;

import com.test.payment_test.modul.Recipient;
import com.test.payment_test.modul.Users;
import com.test.payment_test.role.Role;
import com.test.payment_test.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.userdetails.User;

@SpringBootApplication
public class PaymentTestApplication {


    public static void main(String[] args) {
		SpringApplication.run(PaymentTestApplication.class, args);
	}
//	private final UserService userService;
//	@PostConstruct
//	public void init(){
//		Users users = new Users();
//		users.setSurNameSender("user");
//		users.setRole("SENDER");
//		users.setPhoneNumberSender("777");
//		users.setPassword("777");
//		 userService.save(users);
//		Recipient recipient = new Recipient();
//		recipient.setPassword("7777");
//		recipient.set
//	}

}
