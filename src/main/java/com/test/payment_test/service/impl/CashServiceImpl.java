package com.test.payment_test.service.impl;

import com.test.payment_test.modul.CashB;
import com.test.payment_test.modul.Users;
import com.test.payment_test.repository.CashBRepository;
import com.test.payment_test.repository.UserRepository;
import com.test.payment_test.service.CashService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashServiceImpl implements CashService {
    private final UserRepository userRepository;
    private final CashBRepository cashBRepository;

    public CashServiceImpl(UserRepository userRepository, CashBRepository cashBRepository) {
        this.userRepository = userRepository;
        this.cashBRepository = cashBRepository;
    }

    public CashB sendMoney(CashB cashA) {
        CashB cashB = new CashB();
        cashB.setDate(cashA.getDate());
        cashB.setDescription(cashA.getDescription());
        cashB.setPhoneNumberRecipient(cashA.getPhoneNumberRecipient());
        cashB.setPhoneNumberSender(cashA.getPhoneNumberSender());
        cashB.setSurNameRecipient(cashA.getSurNameRecipient());
        cashB.setSurNameSender(cashA.getSurNameSender());
        cashB.setUniqueCode(cashA.getUniqueCode());
        cashB.setMoney(cashA.getMoney());
        Users sender = userRepository.findByPhoneNumberSender(cashA.getPhoneNumberSender());
        cashB.setSenderId(sender);
//        sender.setSurNameSender(cashA.getSurNameSender());
//        sender.setPhoneNumberSender(cashA.getPhoneNumberSender());
//        sender.setSurNameRecipient(cashA.getSurNameRecipient());
//        sender.setUniqueCode(cashA.getUniqueCode());
//        sender.setDate(cashA.getDate());
//        sender.setDescription(cashA.getDescription());
//        userRepository.save(sender);
        return cashBRepository.save(cashB);

    }

    public List<CashB> getAll() {
        return cashBRepository.findAll();
    }


}
