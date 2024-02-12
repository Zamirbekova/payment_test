package com.test.payment_test.service;

import com.test.payment_test.modul.CashA;
import com.test.payment_test.modul.CashB;
import com.test.payment_test.modul.Recipient;
import com.test.payment_test.modul.Users;
import com.test.payment_test.repository.CashBRepository;
import com.test.payment_test.repository.RecipientRepository;
import com.test.payment_test.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashService {
    private final UserRepository userRepository;
    private final CashBRepository cashBRepository;
    private final RecipientRepository repository;

    public CashService(UserRepository userRepository, CashBRepository cashBRepository, RecipientRepository repository) {
        this.userRepository = userRepository;
        this.cashBRepository = cashBRepository;
        this.repository = repository;
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
        Users user = userRepository.findByPhoneNumberSender(cashA.getPhoneNumberSender());
        Recipient recipient = repository.findByPhoneNumberRecipient(cashA.getPhoneNumberRecipient());
        cashB.setSenderId(user);
        cashB.setRecipientId(recipient);
        user.setMoney(cashB.getMoney());
        user.setSurNameSender(cashB.getSurNameSender());
        user.setPhoneNumberSender(cashB.getPhoneNumberSender());
        user.setSurNameRecipient(cashB.getSurNameRecipient());
        user.setUniqueCode(cashB.getUniqueCode());
        userRepository.save(user);
//        recipient.setMoney(cashB.getMoney());
//        recipient.setSurNameSender(cashB.getSurNameSender());
//        recipient.setPhoneNumberSender(cashB.getPhoneNumberSender());
//        recipient.setSurNameRecipient(cashB.getSurNameRecipient());
//        recipient.setUniqueCode(cashB.getUniqueCode());
//        repository.save(recipient);
        return cashBRepository.save(cashB);

    }

    public List<CashB> getAll() {
        return cashBRepository.findAll();
    }

}
