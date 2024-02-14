package com.test.payment_test.service.impl;

import com.test.payment_test.Status;
import com.test.payment_test.modul.CashB;
import com.test.payment_test.modul.Users;
import com.test.payment_test.repository.CashBRepository;
import com.test.payment_test.repository.UserRepository;
import com.test.payment_test.service.CashService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CashServiceImpl implements CashService {
    private final UserRepository userRepository;
    private final CashBRepository cashBRepository;

    public CashServiceImpl(UserRepository userRepository, CashBRepository cashBRepository) {
        this.userRepository = userRepository;
        this.cashBRepository = cashBRepository;
    }

    @Transactional
    public CashB sendMoney(CashB cashA) {
        CashB cashB = new CashB();
        cashB.setDate(cashA.getDate());
        cashB.setDescription(cashA.getDescription());
        cashB.setPhoneNumberRecipient(cashA.getPhoneNumberRecipient());
        cashB.setPhoneNumberSender(cashA.getPhoneNumberSender());
        cashB.setSurNameRecipient(cashA.getSurNameRecipient());
        cashB.setSurNameSender(cashA.getSurNameSender());
        cashB.setUniqueCode(cashA.getUniqueCode());
        cashB.setStatus(Status.CREATED);

//        Users users = userRepository.findByEmail(cashA.getEmailUser());
//        assert users != null;
//        cashB.setMoney(addMoneyToUser(users.getEmail(), cashA.getMoney()));
//        cashB.setEmailUser(cashA.getEmailUser());
//        users.setSurNameSender(cashA.getSurNameSender());
//        users.setPhoneNumberSender(cashA.getPhoneNumberSender());
//        users.setSurNameRecipient(cashA.getSurNameRecipient());
//        users.setUniqueCode(cashA.getUniqueCode());
//        users.setDate(cashA.getDate());
//        users.setDescription(cashA.getDescription());
//        userRepository.save(users);
        return cashBRepository.save(cashB);

    }

    private BigDecimal addMoneyToUser(String id, BigDecimal additionalAmount) {
        Users user = userRepository.findByEmail(id);

        if (user != null) {
            if (user.getMoney() == null) {
                user.setMoney(additionalAmount);
            }
            if (user.getMoney() == null) {

                BigDecimal currentAmount = null;
                assert false;
                BigDecimal newAmount = currentAmount.add(additionalAmount);
                user.setMoney(newAmount);
                System.out.println("Деньги успешно добавлены.");
            } else {
                System.out.println("Пользователь с ID " + id + " не найден.");
            }
            return user.getMoney();
        }
        return null;
    }

    public List<CashB> getAll() {
        return cashBRepository.findAll();
    }


}
