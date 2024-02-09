package com.test.payment_test.service;

import com.test.payment_test.modul.CashA;
import com.test.payment_test.modul.CashB;
import com.test.payment_test.modul.User;
import com.test.payment_test.repository.CashBRepository;
import com.test.payment_test.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CashService {
    private final UserRepository userRepository;
    private final CashBRepository cashBRepository;

    public CashService(UserRepository userRepository, CashBRepository cashBRepository) {
        this.userRepository = userRepository;
        this.cashBRepository = cashBRepository;
    }

    public CashB sendMoney(CashA cashA) {
        CashB cashB = new CashB();
        cashB.setDate(cashA.getDate());
        cashB.setDescription(cashA.getDescription());
        cashB.setPhoneNumberRecipient(cashA.getPhoneNumberRecipient());
        cashB.setPhoneNumberSender(cashA.getPhoneNumberSender());
        cashB.setSurNameRecipient(cashA.getSurNameRecipient());
        cashB.setSurNameSender(cashA.getSurNameSender());
        cashB.setUniqueCode(cashA.getUniqueCode());
        cashB.setMoney(cashA.getMoney());
        User user = userRepository.findByPhoneNumberSender(cashA.getPhoneNumberSender());
        cashB.setUser(user);
        return cashBRepository.save(cashB);

    }

    public List<CashB> getAll() {
        return cashBRepository.findAll();
    }

}
