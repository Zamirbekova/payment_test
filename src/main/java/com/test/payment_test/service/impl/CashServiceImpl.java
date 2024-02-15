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

        CashB cashB = getCashB(cashA);
        Users current = userRepository.findByPhoneNumber(cashA.getPhoneNumberRecipient());

        if ( current!= null && current.getMoney()==null ) {
            current.setMoney(cashA.getMoney());
            userRepository.save(current);
        }

        if (current != null && current.getMoney() != null) {
            BigDecimal currenSum = current.getMoney();
            BigDecimal newSum = currenSum.add(cashA.getMoney());
            current.setMoney(newSum);
            current.setUniqueCode(cashA.getUniqueCode());
            userRepository.save(current);

        }

        return cashBRepository.save(cashB);

    }

    private static CashB getCashB(CashB cashA) {
        CashB cashB = new CashB();
        cashB.setDate(cashA.getDate());
        cashB.setDescription(cashA.getDescription());
        cashB.setPhoneNumberRecipient(cashA.getPhoneNumberRecipient());
        cashB.setPhoneNumberSender(cashA.getPhoneNumberSender());
        cashB.setSurNameRecipient(cashA.getSurNameRecipient());
        cashB.setSurNameSender(cashA.getSurNameSender());
        cashB.setUniqueCode(cashA.getUniqueCode());
        cashB.setStatus(Status.CREATED);
        cashB.setMoney(cashA.getMoney());
        return cashB;
    }

    private void addMoneyToUser(String phoneNumberRecipient, BigDecimal additionalAmount) {
        Users user = userRepository.findByPhoneNumber(phoneNumberRecipient);
        CashB cashB = new CashB();
        if (user != null) {
            if (user.getMoney() == null) {
                user.setMoney(additionalAmount);
                cashB.setMoney(additionalAmount);
                userRepository.save(user);
            }

            if (user.getMoney() != null) {
                BigDecimal currentAmount = user.getMoney();
                BigDecimal currentAmountCashB = cashB.getMoney();
                assert false;
                BigDecimal newAmount = currentAmount.add(additionalAmount);
                BigDecimal newCashAmount = currentAmountCashB.add(additionalAmount);
                user.setMoney(newAmount);
                cashB.setMoney(newCashAmount);
                userRepository.save(user);
            }
        }
    }



    public List<CashB> getAll() {
        return cashBRepository.findAll();
    }


}
