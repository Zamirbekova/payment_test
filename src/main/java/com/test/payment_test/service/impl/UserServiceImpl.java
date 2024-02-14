package com.test.payment_test.service.impl;

import com.test.payment_test.exception.UserAlreadyExistException;
import com.test.payment_test.modul.CashB;
import com.test.payment_test.modul.Users;
import com.test.payment_test.repository.CashBRepository;
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

    private final UserRepository repository;
    private final CashBRepository cashBRepository;


    @Override
    public Users getMoney(String users) {
        Users users1 = repository.findUsersByUniqueCode(users);
        CashB cashB = cashBRepository.findCashBByUniqueCode(users);
        if (users1.getUniqueCode() != null) {
            if (users1.getUniqueCode().equals(cashB.getUniqueCode())) {
                return users1;
            }

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


