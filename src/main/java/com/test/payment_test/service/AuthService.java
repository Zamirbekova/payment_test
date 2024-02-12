package com.test.payment_test.service;


import com.test.payment_test.repository.AuthRepository;
import com.test.payment_test.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final AuthRepository loginRepository;

}
