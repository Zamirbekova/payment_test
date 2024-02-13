package com.test.payment_test.service.impl;

import com.test.payment_test.exception.UserAlreadyExistException;
import com.test.payment_test.modul.CashB;
import com.test.payment_test.modul.Users;
import com.test.payment_test.repository.CashBRepository;
import com.test.payment_test.repository.RecipientRepository;
import com.test.payment_test.repository.UserRepository;
import com.test.payment_test.role.Role;
import com.test.payment_test.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {

    private final CashBRepository cashBRepository;
    private final UserRepository repository;

    private final RecipientRepository recipientRepository;


    @Override
    public Users getMoney(String users) {
        CashB cashB = cashBRepository.findCashBByUniqueCode(users);
        if (cashB.getUniqueCode().equals(users) ){
            return repository.findUsersByUniqueCode(users);
        }
        return null;
    }

    @Override
    public List<Users> getAll() {
        return repository.findAll();
    }


    public Users registerNewUserAccount(Users userDto) throws UserAlreadyExistException {
        if (repository.existsByEmail(userDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: "
                    + userDto.getEmail());
        }

        Users user = new Users();
        user.setPhoneNumberSender(userDto.getPhoneNumberSender());
        user.setSurNameSender(userDto.getSurNameSender());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setRole(Role.USER);

        return repository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = repository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + username);
        }
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword().toLowerCase(), enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, user.getAuthorities());
    }


}


//    public Users findByCode(String code) {
//        return userRepository.findByUniqueCode(code);
//    }

//    public boolean saveUser(Users user) {
//        Users userFromDB = userRepository.findByPhoneNumberSender(user.getPhoneNumberSender());
//
//        if (userFromDB != null) {
//            return false;
//        }
//
//        user.setRole(Role.SENDER);
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        user.setPhoneNumberSender(user.getPhoneNumberSender());
//        userRepository.save(user);
//        return true;
//    }

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

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Users users = userRepository.findByPhoneNumberSender(username);
//        if (users==null){
//            throw new UsernameNotFoundException("User not found");
//        }
//        return users;
//    }

