package com.test.payment_test.service.impl;


import com.test.payment_test.repository.AuthRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl {
    private final AuthRepository loginRepository;


}
