package com.test.payment_test.service;

import com.test.payment_test.modul.CashB;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CashService {
    public CashB sendMoney(CashB cashA);

    List<CashB> getAll();
}
