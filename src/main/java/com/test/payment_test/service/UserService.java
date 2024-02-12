package com.test.payment_test.service;

import com.test.payment_test.modul.Recipient;
import com.test.payment_test.modul.Users;
import com.test.payment_test.repository.CashBRepository;
import com.test.payment_test.repository.RecipientRepository;
import com.test.payment_test.repository.UserRepository;
import com.test.payment_test.role.Role;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final CashBRepository cashBRepository;
    private final UserRepository userRepository;

    private final RecipientRepository recipientRepository;
    private  BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(CashBRepository cashBRepository, UserRepository userRepository, RecipientRepository recipientRepository) {
        this.cashBRepository = cashBRepository;
        this.userRepository = userRepository;
        this.recipientRepository = recipientRepository;
    }

    @Transactional
    public Recipient getMoney(Recipient recipient) {
        return recipientRepository.getRecipientByUniqueCode(recipient.getUniqueCode());
    }

    public List<Recipient> getMoney() {
        return recipientRepository.findAll();
    }


//    public Users findByCode(String code) {
//        return userRepository.findByUniqueCode(code);
//    }

    public boolean saveUser(Users user) {
        Users userFromDB = userRepository.findByPhoneNumberSender(user.getPhoneNumberSender());

        if (userFromDB != null) {
            return false;
        }

        user.setRole(Role.SENDER);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setPhoneNumberSender(user.getPhoneNumberSender());
        userRepository.save(user);
        return true;
    }

//    public Users findByPhoneNumber(String phoneNumberSender) {
//
//        return userRepository.findByPhoneNumberSender(phoneNumberSender);
//    }
//
//    @Transactional
//    public Users register(Users users) {
//        Users users1 = new Users();
//        UserDetails rec = User.withDefaultPasswordEncoder()
//                .username(users.getPhoneNumberRecipient())
//                .password(users.getPassword())
//                .roles(String.valueOf(Role.SENDER))
//                .build();
//        users1.setPhoneNumberSender(rec.getUsername());
//        users1.setPassword(rec.getPassword());
//        users1.setRole(Role.SENDER);
//        return userRepository.save(users1);
//
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepository.findByPhoneNumberSender(username);
        if (users==null){
            throw new UsernameNotFoundException("User not found");
        }
        return users;
    }
}

