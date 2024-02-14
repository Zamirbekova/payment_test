package com.test.payment_test.service.impl;

import com.test.payment_test.Status;
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
    public CashB getMoneyUser(String code) {
//        Users users1 = repository.findUsersByUniqueCode(code);
        CashB cashB = cashBRepository.findCashBByUniqueCode(code);

        if (cashB.getUniqueCode() != null) {
            if (cashB.getUniqueCode().equals(code)) {
                cashB.setStatus(Status.DONE);
                return cashBRepository.findCashBByUniqueCode(code);
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
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setSurName(userDto.getSurName());
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


