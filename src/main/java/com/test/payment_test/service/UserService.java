package com.test.payment_test.service;

import com.test.payment_test.exception.UserAlreadyExistException;
import com.test.payment_test.modul.Users;

import java.util.List;

public interface UserService {

    Users getMoney(String code);

    List<Users> getAll();

    Users registerNewUserAccount(Users userDto) throws UserAlreadyExistException;

    Object loadUserByUsername(String email);

}
