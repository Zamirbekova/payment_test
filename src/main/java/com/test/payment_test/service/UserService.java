package com.test.payment_test.service;

import com.test.payment_test.modul.CashB;
import com.test.payment_test.modul.User;
import com.test.payment_test.repository.CashBRepository;
import com.test.payment_test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private final CashBRepository cashBRepository;
    private final UserRepository userRepository;
    @Autowired
    public UserService(CashBRepository cashBRepository, UserRepository userRepository) {
        this.cashBRepository = cashBRepository;
        this.userRepository = userRepository;
    }

    public User getMoney(String uniqueCode) {
        CashB cashB = cashBRepository.findByUniqueCode(uniqueCode);

        if (cashB.getUniqueCode().equals(uniqueCode)){

            Map<BigDecimal, BigDecimal> cashSum= new HashMap<>();
            User user = new User();
            user.setId(cashB.getUser().getId());
            user.setPhoneNumberSender(cashB.getPhoneNumberSender());
            user.setSurNameSender(cashB.getSurNameSender());
            user.setUniqueCode(cashB.getUniqueCode());
//            cashSum.merge(cashB.getMoney(),user.getMoney(),BigDecimal::subtract);
           user.setMoney(cashSum.merge(cashB.getMoney(),user.getMoney(),BigDecimal::subtract));
            return userRepository.save(user);

        }
        return null;
    }
    public User findByCode(String code){
        return userRepository.findByUniqueCode(code);
    }
}
